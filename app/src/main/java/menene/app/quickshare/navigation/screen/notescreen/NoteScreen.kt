package menene.app.quickshare.navigation.screen.notescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.BasicRichTextEditor
import kotlinx.coroutines.delay
import menene.app.quickshare.R
import menene.app.quickshare.presentation.button.TextOptionButton

@Composable
fun NoteScreen(
    noteId: String
) {
    val viewModel = hiltViewModel<NoteViewModel>()
    val note by viewModel.noteState.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf("") }
    var lastTitle by remember { mutableStateOf("") }
    val state = rememberRichTextState()

    LaunchedEffect(Unit) {
        viewModel.initialize(noteId)
    }

    LaunchedEffect(note) {
        note?.let{
            title = it.title
            lastTitle = it.title
            state.setHtml(it.content)
        }
    }

    LaunchedEffect(title) {
        delay(500)
        if (title != lastTitle) {
            viewModel.saveNote(note!!.copy(title = title))
            lastTitle = title
        }
    }

    LaunchedEffect(state.selection.toString()) {
        delay(500)
        if (state.toText() != note?.content) {
            viewModel.saveNote(note!!.copy(content = state.toHtml()))
        }
    }

    Column {
        TextField(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            textStyle = MaterialTheme.typography.labelLarge,
            placeholder = { Text(stringResource(R.string.title)) },
            singleLine = true,
        )

        BasicRichTextEditor(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            state = state,
            textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TODO duzo lepsze opcje edycji tekstu
        // TODO zamiast row to tutaj trzeba zrobic kinda bottom sheet
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextOptionButton(
                Icons.Default.FormatBold
            ) {
                state.addSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
            }
            TextOptionButton(
                Icons.AutoMirrored.Filled.List
            ) {
                state.toggleOrderedList()
            }
            TextOptionButton(
                Icons.Default.FormatAlignCenter
            ) {
                state.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
            }
            TextOptionButton(
                Icons.Default.FormatUnderlined
            ) {
                state.addSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            }
        }
    }
}
