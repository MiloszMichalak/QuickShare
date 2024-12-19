package menene.app.quickshare.data.repository

import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User
import menene.app.quickshare.navigation.screen.authscreen.AuthState
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRef: DatabaseReference,
    private val auth: FirebaseAuth,
    private val userId: Lazy<String>,
    private val context: Application
) {
    suspend fun getUser(): User? {
        return try {
            userRef.child(userId.get()).get().await().getValue(User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun saveUserData(user: User, id: String): Boolean {
        return try {
            userRef.child(id).setValue(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun signUp(email: String, password: String, scope: CoroutineScope): AuthState {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()

            val currentUser = auth.currentUser

            currentUser?.let {
                it.sendEmailVerification().await()

                Toast.makeText(
                    context,
                    context.getString(R.string.sent_verification_email),
                    Toast.LENGTH_SHORT
                ).show()

                scope.launch {
                    saveUserData(User(email.substringBefore("@"), ""), it.uid)
                }
                auth.signOut()
            }
            AuthState.Success
        } catch (e: Exception) {
            AuthState.Error(context.getString(R.string.email_taken))
        }
    }


    suspend fun logIn(email: String, password: String): AuthState {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()

            val isEmailVerified = auth.currentUser?.isEmailVerified

            if (isEmailVerified == true) {
                AuthState.Success
            } else {
                auth.signOut()
                AuthState.Error(context.getString(R.string.email_not_verified))
            }
        } catch (e: Exception) {
            AuthState.Error(context.getString(R.string.invalid_credentials))
        }
    }

    fun logOut(){
        auth.signOut()
    }

    // TODO logowanie przez google
//    suspend fun logInWithGoogle(){
//
//    }
}