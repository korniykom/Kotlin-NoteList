package com.korniykom.notelist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>
}