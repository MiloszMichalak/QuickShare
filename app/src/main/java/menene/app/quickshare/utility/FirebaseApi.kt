package menene.app.quickshare.utility

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

object FirebaseApi {
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
