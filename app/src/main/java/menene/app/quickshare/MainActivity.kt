package menene.app.quickshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.graphs.NavGraph
import menene.app.quickshare.ui.theme.QuickShareTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickShareTheme {
                Scaffold { innerPadding ->
                    val navController = rememberNavController()
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = innerPadding.calculateTopPadding(),
                                bottom = innerPadding.calculateBottomPadding(),
                                start = 16.dp,
                                end = 16.dp
                            ),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        NavGraph(
                            navController = navController,
                            startDestination = if (Firebase.auth.currentUser == null) Screen.AuthGraph else Screen.ListGraph
                        )
                    }
                }
            }
        }
    }
}