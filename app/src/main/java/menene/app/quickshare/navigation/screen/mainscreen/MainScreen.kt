package menene.app.quickshare.navigation.screen.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import menene.app.quickshare.R
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.SharedViewModel
import menene.app.quickshare.presentation.TopAppBar
import menene.app.quickshare.utility.FirebaseApi.getCurrentUserUid

@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val user by sharedViewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        sharedViewModel.getUser(Firebase.getCurrentUserUid())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                user,
                navController
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                var noteId by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = noteId,
                    onValueChange = { noteId = it },
                    label = { Text(stringResource(id = R.string.enter_code))},
                    singleLine = true,
                    keyboardActions = KeyboardActions {
                        navController.navigate(Screen.NoteScreen(noteId))
                    }
                )
            }
        }
    )
}