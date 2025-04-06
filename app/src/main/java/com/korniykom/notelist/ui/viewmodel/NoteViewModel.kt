package com.korniykom.notelist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            noteDao.getAllNotes().collect() { notes ->
                sortListOfNotes(notes)
            }
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            noteDao.deleteNote(note)
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insertNote(note)
            sortListOfNotes(_uiState.value.noteList)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.updateNote(note)
            sortListOfNotes(_uiState.value.noteList)
        }
    }

    fun updateCurrentNote(note: Note) {
        _uiState.update { currentState ->
            currentState.copy(
                noteToUpdate = note
            )
        }
    }

    fun setIsNewNote(value: Boolean) {
        _uiState.update { currentState ->
        currentState.copy(
            isNewNote = value
        )
        }
    }

    private fun sortListOfNotes(notes: List<Note>) {
        _uiState.update { currentState ->
            currentState.copy(
                noteList = notes.sortedByDescending { it.createdAt }
            )
        }
    }
}