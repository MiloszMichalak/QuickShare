package menene.app.quickshare.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import menene.app.quickshare.R
import menene.app.quickshare.data.model.User
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.presentation.image.ProfilePicture

@Composable
fun TopAppBar(
    user: User?,
    navController: NavHostController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceBright)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(id = R.string.app_name)
        )
        ProfilePicture(
            user,
        ) { navController.navigate(Screen.UserScreen) }
    }
}