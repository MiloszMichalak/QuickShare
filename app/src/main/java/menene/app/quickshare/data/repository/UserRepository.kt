package menene.app.quickshare.data.repository

import kotlinx.coroutines.tasks.await
import menene.app.quickshare.data.model.User
import menene.app.quickshare.utility.FirebaseApi.getUsersReference

class UserRepository {
    private val userRef = getUsersReference()

    suspend fun getUser(userId: String): User? {
        return try {
            userRef.child(userId).get().await().getValue(User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveUserData(user: User, id: String): Boolean {
        return try {
            userRef.child(id).setValue(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}