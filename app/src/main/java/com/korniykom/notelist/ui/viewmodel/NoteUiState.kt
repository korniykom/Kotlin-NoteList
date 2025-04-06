package com.korniykom.notelist.ui.viewmodel

import com.korniykom.notelist.data.Note
import java.time.LocalDateTime

data class NoteUiState(
    val noteList: List<Note> = emptyList(),
    val noteToUpdate: Note = Note(id = -1, title = "Empty note", content = "No content", createdAt = LocalDateTime.MIN),
    val isNewNote: Boolean = true
)