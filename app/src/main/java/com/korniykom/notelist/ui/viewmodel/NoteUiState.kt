package com.korniykom.notelist.ui.viewmodel

import com.korniykom.notelist.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class NoteUiState(
    val noteList: List<Note> = emptyList(),
    val noteToUpdate: Note = Note(id = -1, title = "Empty note", content = "No content", createdAt = -1),
    val isNewNote: Boolean = true
)