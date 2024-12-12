package menene.app.quickshare.di

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import menene.app.quickshare.data.repository.NoteRepository
import menene.app.quickshare.data.repository.UserRepository
import menene.app.quickshare.utility.FirebaseApi
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        @Named("userRef") userReference: DatabaseReference,
        auth: FirebaseAuth,
        application: Application
    ): UserRepository {
        return UserRepository(userReference, auth, application)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(@Named("noteRef") noteReference: DatabaseReference): NoteRepository {
        return NoteRepository(noteReference)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    @Named("userRef")
    fun provideUserDatabaseReference(): DatabaseReference {
        return FirebaseApi.getUsersReference()
    }

    @Provides
    @Singleton
    @Named("noteRef")
    fun provideNoteDatabaseReference(): DatabaseReference {
        return FirebaseApi.getNotesReference()
    }
}