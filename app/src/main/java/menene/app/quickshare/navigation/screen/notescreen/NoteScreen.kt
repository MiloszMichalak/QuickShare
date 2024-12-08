package menene.app.quickshare.navigation.screen.notescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import menene.app.quickshare.R

@Composable
fun NoteScreen(
    noteId: String
) {
    val viewModel = viewModel<NoteViewModel>()
    val note by viewModel.noteState.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var lastTitle by remember { mutableStateOf("") }
    var lastContent by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getNote(noteId)
        viewModel.observeNote(noteId)
    }

    LaunchedEffect(note) {
        note?.let{
            title = it.title
            content = it.content
            lastTitle = it.title
            lastContent = it.content
        }
    }

    LaunchedEffect(title) {
        delay(500)
        if (title != lastTitle) {
            viewModel.saveNote(note!!.copy(title = title))
            lastTitle = title
        }
    }

    LaunchedEffect(content) {
        delay(500)
        if (content != lastContent) {
            viewModel.saveNote(note!!.copy(content = content))
            lastContent = content
        }
    }

    Column {
        TextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.height(64.dp),
            label = { Text(stringResource(R.string.title)) }
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxSize(),
        )
    }
}
