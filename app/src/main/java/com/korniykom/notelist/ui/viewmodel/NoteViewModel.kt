package com.korniykom.notelist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.data.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            addNote(Note(id = 0, title = "My title", content = "New Content"))
        }
        _uiState.update { currentState ->
            currentState.copy(
                noteList = noteDao.getAllNotes()
            )
        }
    }

    suspend fun addNote(note: Note) = noteDao.insertNote(note)
    suspend fun removeNote(note: Note) = noteDao.deleteNote(note)
}