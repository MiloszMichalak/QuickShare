package menene.app.quickshare.utility

import android.util.Patterns

object Validation {
    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }

    fun checkPasswords(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}