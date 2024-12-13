package menene.app.quickshare.navigation.screen.authscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import menene.app.quickshare.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _logInMessage = MutableStateFlow<AuthState>(AuthState.Idle)
    var logInMessage = _logInMessage

    private val _isCompleteSignUp = MutableStateFlow<AuthState>(AuthState.Idle)
    var isCompleteSignUp = _isCompleteSignUp

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            val message = userRepository.logIn(email, password)
            _logInMessage.value = message
        }
    }

    fun signUp(email: String, password: String, scope: CoroutineScope) {
        viewModelScope.launch {
            val isComplete = userRepository.signUp(email, password, scope)
            _isCompleteSignUp.value = isComplete
        }
    }

    fun resetAuthState() {
        _isCompleteSignUp.value = AuthState.Idle
    }
}