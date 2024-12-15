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
    private val noteReference: DatabaseReference,
    private val userReference: DatabaseReference,
    private val userId: String
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
                saveNoteInUser(id)
                noteModel
            }
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun saveNoteInUser(id: String) {
        userReference.child(userId).child("userNotes").child(id)
            .setValue(true).await()
    }

    private suspend fun getNotesIds(): List<String> {
        return try {
            userReference.child(userId).child("userNotes").get().await()
                .children.mapNotNull { it.key }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserNotes(): List<Note> {
        return try {
            val notesIds = getNotesIds()
            notesIds.mapNotNull { getNote(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

//    suspend fun getAllNotes(): List<Note> {
//        return try {
//            noteReference.get().await().
//            children.mapNotNull { it.getValue<Note>() }
//        } catch (e: Exception) {
//            emptyList()
//        }
//    }

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