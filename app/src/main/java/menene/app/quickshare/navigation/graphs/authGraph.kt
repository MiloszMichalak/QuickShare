package menene.app.quickshare.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.authscreen.LoginScreen
import menene.app.quickshare.navigation.screen.authscreen.OnBoardingScreen
import menene.app.quickshare.navigation.screen.authscreen.RegisterScreen

fun NavGraphBuilder.authGraph(navController: NavHostController){
    navigation<Screen.AuthGraph>(
        startDestination = Screen.OnBoarding,
    ){
        composable<Screen.OnBoarding> {
            OnBoardingScreen(navController)
        }
        composable<Screen.Login> {
            LoginScreen(navController)
        }
        composable<Screen.Register> {
            RegisterScreen(navController)
        }
    }
}