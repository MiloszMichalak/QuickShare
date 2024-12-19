package menene.app.quickshare.presentation.button

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TextOptionButton(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    IconButton (
        onClick = onClick,
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}