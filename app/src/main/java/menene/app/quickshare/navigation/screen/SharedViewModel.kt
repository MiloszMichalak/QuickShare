package menene.app.quickshare.navigation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import menene.app.quickshare.data.model.Note
import menene.app.quickshare.data.model.User
import menene.app.quickshare.data.repository.NoteRepository
import menene.app.quickshare.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository,
): ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState

    private val _userNotes = MutableStateFlow<List<Note>>(emptyList())
    val userNotes = _userNotes

    fun getUser(){
        viewModelScope.launch {
            val user = userRepository.getUser()
            _userState.value = user
        }
    }

    fun getUserNotes(){
        viewModelScope.launch {
            _userNotes.value = noteRepository.getUserNotes()
        }
    }
}