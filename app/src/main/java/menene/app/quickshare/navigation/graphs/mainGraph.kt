package menene.app.quickshare.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.SharedViewModel
import menene.app.quickshare.navigation.screen.notescreen.NoteScreen
import menene.app.quickshare.navigation.screen.mainscreen.MainScreen
import menene.app.quickshare.navigation.screen.userscreen.UserScreen


fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    navigation<Screen.ListGraph>(
        startDestination = Screen.MainScreen,
    ) {
        composable<Screen.MainScreen> {
            MainScreen(
                navController = navController,
                sharedViewModel
            )
        }
        composable<Screen.NoteScreen>{ backStackEntry ->
            val note = backStackEntry.toRoute<Screen.NoteScreen>()
            NoteScreen(noteId = note.noteId,)
        }
        composable<Screen.UserScreen> {
            UserScreen(sharedViewModel)
        }
    }
}