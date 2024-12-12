package menene.app.quickshare.utility

object Validation {
    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }

    fun checkPasswords(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}