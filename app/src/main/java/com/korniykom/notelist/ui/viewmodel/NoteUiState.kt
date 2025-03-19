package com.korniykom.notelist.ui.viewmodel

import com.korniykom.notelist.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class NoteUiState(
    val noteList: Flow<List<Note>> = flowOf(emptyList())
)