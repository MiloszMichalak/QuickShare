package menene.app.quickshare.navigation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import menene.app.quickshare.data.model.User
import menene.app.quickshare.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState

    fun getUser(userId: String){
        viewModelScope.launch {
            val user = userRepository.getUser(userId)
            _userState.value = user
        }
    }
}