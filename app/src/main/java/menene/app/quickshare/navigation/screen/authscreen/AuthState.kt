package menene.app.quickshare.navigation.screen.authscreen

sealed class AuthState{
    data object Success: AuthState()
    data class Error(val message: String): AuthState()
    data object Idle: AuthState()
}