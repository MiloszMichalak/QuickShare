package menene.app.quickshare.navigation.screen.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import menene.app.quickshare.data.model.User
import menene.app.quickshare.data.repository.UserRepository

class MainViewModel: ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState

    private val userRepository = UserRepository()

    fun getUser(userId: String){
        viewModelScope.launch {
            val user = userRepository.getUser(userId)
            _userState.value = user
        }
    }
}