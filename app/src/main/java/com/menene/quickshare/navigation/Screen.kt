package com.menene.quickshare.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Register: Screen(route = "register")
    object Login: Screen(route = "login")
}