package menene.app.quickshare.navigation.screen.userscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import menene.app.quickshare.navigation.screen.SharedViewModel
import menene.app.quickshare.presentation.image.ProfilePicture

@Composable
fun UserScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val user by sharedViewModel.userState.collectAsStateWithLifecycle()

    Surface{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfilePicture(
                user = user,
                height = 120
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user?.name ?: "",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}