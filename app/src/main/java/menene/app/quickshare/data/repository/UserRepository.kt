package menene.app.quickshare.data.repository

import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRef: DatabaseReference,
    private val auth: FirebaseAuth,
    private val context: Application
) {
    suspend fun getUser(userId: String): User? {
        return try {
            userRef.child(userId).get().await().getValue(User::class.java)
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

    fun signUp(email: String, password: String, scope: CoroutineScope): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        currentUser?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.sent_verification_email),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        if (currentUser != null) {
                            scope.launch {
                                saveUserData(User(email.substringBefore("@"), ""), currentUser.uid)
                            }
                        }
                        auth.signOut()
                    }
                }
            true
        } catch (e: Exception) {
            false
        }
    }


    fun logIn(email: String, password: String): String{
        var returnString = ""
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val currentUser = auth.currentUser
                val isEmailVerified = currentUser?.isEmailVerified
                returnString = if (!task.isSuccessful && isEmailVerified == false) {
                    context.getString(R.string.email_not_verified)
                } else {
                    context.getString(R.string.invalid_email)
                }
            }
        return returnString
    }
}