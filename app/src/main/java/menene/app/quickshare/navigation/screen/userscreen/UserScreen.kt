package menene.app.quickshare.navigation.screen.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.BasicRichTextEditor
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

    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.padding(top = 16.dp),
    ){
        Column{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                ProfilePicture(
                    user = user,
                    height = 120,
                )
                Box{
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd),
                        onClick = { isContextMenuVisible = true },
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.more_icon),
                        )
                    }

                    DropdownMenu(
                        expanded = isContextMenuVisible,
                        onDismissRequest = { isContextMenuVisible = false },
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                isContextMenuVisible = false
                                navController.navigate(Screen.AuthGraph) { popUpTo(Screen.ListGraph) }
                                sharedViewModel.logOut()
                            },
                            text = { Text(stringResource(R.string.logout)) }
                        )
                    }
                }
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
    val scrollState = rememberScrollState()
    val state = rememberRichTextState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .height(120.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.NoteScreen(note.id))
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = stringResource(id = R.string.note_id, note.id),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            )
        }
        BasicRichTextEditor(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            state = state.setHtml(note.content),
            enabled = false,
            textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurface),
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
