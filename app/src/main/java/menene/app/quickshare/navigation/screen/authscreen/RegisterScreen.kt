package menene.app.quickshare.navigation.screen.authscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import menene.app.quickshare.R
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.presentation.button.WideButton
import menene.app.quickshare.presentation.text.ErrorText
import menene.app.quickshare.presentation.text.HeaderText
import menene.app.quickshare.presentation.textfield.EmailOutlinedTextField
import menene.app.quickshare.presentation.textfield.PasswordOutlinedTextField
import menene.app.quickshare.utility.FirebaseApi
import menene.app.quickshare.utility.Validation


@Composable
fun RegisterScreen(navController: NavHostController) {
    Column {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        HeaderText(
            text = stringResource(id = R.string.get_started),
        )

        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var confirmPasswordText by remember { mutableStateOf("") }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf(false) }
        var confirmPasswordError by remember { mutableStateOf(false) }


        EmailOutlinedTextField(
            value = emailText,
            emailError.isNotEmpty(),
            onValueChange = {
                emailText = it
                emailError = ""
            },
        )
        ErrorText(emailError)


        PasswordOutlinedTextField(
            value = passwordText,
            label = stringResource(id = R.string.password),
            passwordError,
            onValueChange = {
                passwordText = it
                passwordError = false
            }
        )
        ErrorText(if (passwordError) stringResource(id = R.string.short_password) else "")

        PasswordOutlinedTextField(
            value = confirmPasswordText,
            label = stringResource(id = R.string.confirm_password),
            confirmPasswordError,
            onValueChange = {
                confirmPasswordText = it
                confirmPasswordError = false
            },
        )
        ErrorText(if (confirmPasswordError) stringResource(id = R.string.password_mismatch) else "")

        WideButton(
            text = stringResource(id = R.string.sign_up),
            onClick = {
                emailError = if (!Validation.validateEmail(emailText)) context.getString(R.string.invalid_email) else ""
                passwordError = !Validation.validatePassword(passwordText)
                confirmPasswordError = !Validation.checkPasswords(passwordText, confirmPasswordText)

                if (emailError.isEmpty() && !passwordError && !confirmPasswordError) {
                    FirebaseApi.signUp(emailText, passwordText, context, scope, onComplete = { isComplete ->
                            if (isComplete) {
                                navController.popBackStack()
                            } else {
                                emailError = context.getString(R.string.email_taken)
                            }
                        })
                }
            }
        )
    }
}
