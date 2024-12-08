package menene.app.quickshare.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{

    @Serializable
    data object Login: Screen()

    @Serializable
    data object Register: Screen()

    @Serializable
    data object OnBoarding: Screen()

    @Serializable
    data object MainScreen: Screen()

    @Serializable
    data class NoteScreen(
        val noteId: String
    ): Screen()


    @Serializable
    data object AuthGraph: Screen()

    @Serializable
    data object ListGraph: Screen()
}