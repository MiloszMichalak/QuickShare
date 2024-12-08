package menene.app.quickshare.presentation.text

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ErrorText(
    errorText: String
) {
    Text(
        text = errorText,
        color = MaterialTheme.colorScheme.error,
    )
}