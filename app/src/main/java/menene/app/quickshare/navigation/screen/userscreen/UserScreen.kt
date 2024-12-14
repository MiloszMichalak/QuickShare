package menene.app.quickshare.navigation.screen.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import menene.app.quickshare.R
import menene.app.quickshare.data.model.Note
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.SharedViewModel
import menene.app.quickshare.presentation.image.ProfilePicture

@Composable
fun UserScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val user by sharedViewModel.userState.collectAsStateWithLifecycle()
    val userNotes by sharedViewModel.userNotes.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        sharedViewModel.getUserNotes()
    }

    Surface{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row{
                ProfilePicture(
                    user = user,
                    height = 120,
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_icon),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user?.name ?: "",
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.your_notes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp))

            LazyColumn {
                items(userNotes) { note ->
                    NoteItem(note, navController)
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.NoteScreen(note.id))
            }
    ) {
        Text(
            text = note.title,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = note.content,
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
