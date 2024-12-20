package menene.app.quickshare.navigation.screen.notescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import menene.app.quickshare.data.model.Note
import menene.app.quickshare.data.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    private val _noteState = MutableStateFlow<Note?>(null)
    val noteState = _noteState

    fun initialize(id: String){
        getNote(id)
        observeNote(id)
    }

    fun saveNote(note: Note){
        viewModelScope.launch {
            noteRepository.saveNote(note)
        }
    }

    private fun getNote(id: String){
        viewModelScope.launch {
            val note = noteRepository.getNote(id)
            _noteState.value = note
        }
    }

    private fun observeNote(id: String){
        viewModelScope.launch {
            noteRepository.observeNote(id){
                _noteState.value = it
            }
        }
    }
}