package com.menene.quickshare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.menene.quickshare.screen.HomeScreen
import com.menene.quickshare.screen.LoginScreen
import com.menene.quickshare.screen.RegisterScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen()
        }
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen()
        }
    }
}