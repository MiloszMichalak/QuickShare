package menene.app.quickshare.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await
import menene.app.quickshare.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteReference: DatabaseReference
) {
    suspend fun saveNote(note: Note): Boolean {
        return try {
            noteReference.child(note.id).setValue(note).await()
            true
        } catch (e: Exception){
            false
        }
    }

    suspend fun getNote(id: String): Note? {
        val note = noteReference.child(id).get().await()
        return try {
            if (note.exists()){
                note.getValue<Note>()
            } else {
                val noteModel = Note(id = id)
                saveNote(noteModel)
                noteModel
            }
        } catch (e: Exception) {
            null
        }
    }

    fun observeNote(id: String, callback: (Note?) -> Unit){
        noteReference.child(id).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val note = snapshot.getValue(Note::class.java)
                callback(note)
            }

            override fun onCancelled(error: DatabaseError) {
                // If error
            }
        })
    }
}