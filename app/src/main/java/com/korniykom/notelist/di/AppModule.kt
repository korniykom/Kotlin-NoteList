package com.korniykom.notelist.di

import android.content.Context
import androidx.room.Room
import com.korniykom.notelist.data.NoteDao
import com.korniykom.notelist.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    fun provideDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }
}