package menene.app.quickshare.presentation.image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User

@Composable
fun ProfilePicture(
    user: User?,
    modifier: Modifier = Modifier,
    height: Int = 48,
    onClick: () -> Unit = {}
) {
    AsyncImage(
        placeholder = rememberVectorPainter(Icons.Rounded.Person),
        fallback = rememberVectorPainter(Icons.Rounded.Person),
        error = rememberVectorPainter(Icons.Rounded.Person),
        model = ImageRequest.Builder(LocalContext.current)
            .data( user?.imageUri ?: "")
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.user_profile_image),
        modifier = modifier
            .height(height.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}