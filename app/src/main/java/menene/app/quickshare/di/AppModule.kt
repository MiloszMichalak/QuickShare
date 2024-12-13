package menene.app.quickshare.di

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import menene.app.quickshare.data.repository.NoteRepository
import menene.app.quickshare.data.repository.UserRepository
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
    fun provideUserDatabaseReference(instance: FirebaseDatabase): DatabaseReference {
        return instance.getReference("users")
    }

    @Provides
    @Singleton
    @Named("noteRef")
    fun provideNoteDatabaseReference(instance: FirebaseDatabase): DatabaseReference {
        return instance.getReference("notes")
    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(): FirebaseDatabase{
        return Firebase.database("https://quickshare-3b4e9-default-rtdb.europe-west1.firebasedatabase.app/")
    }
}