package menene.app.quickshare.presentation.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderText(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .padding(bottom = 32.dp),
    )
}