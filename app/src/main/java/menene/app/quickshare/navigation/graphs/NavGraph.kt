package menene.app.quickshare.navigation.graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.SharedViewModel
import menene.app.quickshare.navigation.screen.authscreen.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Screen,
) {
    val time = 500
    val sharedViewModel: SharedViewModel = hiltViewModel<SharedViewModel>()
    val authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
            )
        },
    ) {
        authGraph(
            navController,
            authViewModel,
        )

        mainGraph(
            navController,
            sharedViewModel
        )
    }
}