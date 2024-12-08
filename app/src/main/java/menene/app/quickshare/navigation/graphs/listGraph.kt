package menene.app.quickshare.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import menene.app.quickshare.data.model.Note
import menene.app.quickshare.navigation.Screen
import menene.app.quickshare.navigation.screen.notescreen.NoteScreen
import menene.app.quickshare.navigation.screen.mainscreen.MainScreen


fun NavGraphBuilder.listGraph(
    navController: NavHostController
) {
    navigation<Screen.ListGraph>(
        startDestination = Screen.MainScreen,
    ) {
        composable<Screen.MainScreen> {
            MainScreen(navController = navController)
        }
        composable<Screen.NoteScreen>{ backStackEntry ->
            val note = backStackEntry.toRoute<Screen.NoteScreen>()
            NoteScreen(noteId = note.noteId)
        }
    }
}