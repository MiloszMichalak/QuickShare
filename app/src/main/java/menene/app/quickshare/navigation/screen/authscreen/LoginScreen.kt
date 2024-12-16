package menene.app.quickshare.navigation.screen.authscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import menene.app.quickshare.R
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.presentation.button.WideButton
import menene.app.quickshare.presentation.text.ErrorText
import menene.app.quickshare.presentation.text.HeaderText
import menene.app.quickshare.presentation.textfield.EmailOutlinedTextField
import menene.app.quickshare.presentation.textfield.PasswordOutlinedTextField
import menene.app.quickshare.utility.Validation

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    Column {
        HeaderText(
            text = stringResource(id = R.string.welcome_back)
        )

        val context = LocalContext.current

        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf(false) }

        val error: AuthState by authViewModel.logInMessage.collectAsStateWithLifecycle()
        when (error) {
            is AuthState.Error -> emailError = (error as AuthState.Error).message
            is AuthState.Success -> {
                navController.navigate(Screen.ListGraph){
                    popUpTo(Screen.AuthGraph) {inclusive = true}
                }
            }
            AuthState.Idle -> {}
        }

        EmailOutlinedTextField(
            value = emailText,
            emailError.isNotEmpty(),
            onValueChange = {
                emailText = it
                emailError = ""
            }
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
        ErrorText(if (passwordError) stringResource(id = R.string.invalid_password) else "")

        WideButton(
            text = stringResource(id = R.string.log_in),
            onClick = {
                emailError = if (!Validation.validateEmail(emailText)) context.getString(R.string.invalid_email) else ""
                passwordError = !Validation.validatePassword(passwordText)

                if (emailError.isEmpty() && !passwordError){
                    authViewModel.logIn(emailText, passwordText)
                }
            }
        )
    }
}
