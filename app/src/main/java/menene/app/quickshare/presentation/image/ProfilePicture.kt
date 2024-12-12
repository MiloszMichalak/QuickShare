package menene.app.quickshare.presentation.image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User

@Composable
fun ProfilePicture(
    user: User?,
    height: Int = 48,
    onClick: () -> Unit = {}
) {
    AsyncImage(
        model = user?.imageUri ?: "",
        contentDescription = stringResource(id = R.string.user_profile_image),
        modifier = Modifier
            .height(height.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
}