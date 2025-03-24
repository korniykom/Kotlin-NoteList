package com.korniykom.notelist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.korniykom.notelist.MainActivity
import com.korniykom.notelist.NoteApplication
import com.korniykom.notelist.data.NoteDao
import com.korniykom.notelist.data.NoteDatabase
import com.korniykom.notelist.ui.viewmodel.NoteViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(application: NoteApplication)
    fun inject(activity: MainActivity)
}

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext
}

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()
}

@Module
class ViewModelModule {
    @Provides
    fun provideViewModel(noteDao: NoteDao): NoteViewModel {
        return NoteViewModel(noteDao)
    }
}