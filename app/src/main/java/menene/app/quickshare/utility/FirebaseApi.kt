package menene.app.quickshare.utility

import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User
import menene.app.quickshare.data.repository.UserRepository

object FirebaseApi {
    fun signUp(
        email: String,
        password: String,
        context: Context,
        scope: CoroutineScope,
        onComplete: (Boolean) -> Unit) {
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    currentUser?.sendEmailVerification()
                        ?.addOnSuccessListener {
                            Toast.makeText(context, context.getString(R.string.sent_verification_email), Toast.LENGTH_SHORT)
                                .show()
                        }
                    if (currentUser != null) {
                        scope.launch {
                            UserRepository().saveUserData(User(email.substringBefore("@"), ""))
                        }
                    }
                    auth.signOut()
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }

    fun logIn(email: String, password: String, context: Context, onComplete: (String) -> Unit) {
        val auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val currentUser = auth.currentUser
                val isEmailVerified = currentUser?.isEmailVerified
                if (task.isSuccessful && isEmailVerified == true) {
                    onComplete("")
                } else {
                    if (isEmailVerified == false) {
                        onComplete(context.getString(R.string.email_not_verified))
                    } else {
                        onComplete(context.getString(R.string.invalid_email))
                    }
                }
            }
    }

    private fun Firebase.getInstance(): FirebaseDatabase {
        return database("https://quickshare-3b4e9-default-rtdb.europe-west1.firebasedatabase.app")
    }

    fun getUsersReference(): DatabaseReference {
        return Firebase.getInstance().getReference("users")
    }

    fun Firebase.getCurrentUserUid(): String {
        return auth.currentUser?.uid ?: ""
    }

    fun getNotesReference(): DatabaseReference {
        return Firebase.getInstance().getReference("notes")
    }
}
