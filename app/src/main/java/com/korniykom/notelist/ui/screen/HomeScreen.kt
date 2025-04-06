package com.korniykom.notelist.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.ui.components.FAB
import com.korniykom.notelist.ui.components.ListItem
import com.korniykom.notelist.ui.viewmodel.NoteViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
    onNavigateToNoteCreation: (note: Note) -> Unit,
    onNoteDelete: (note: Note) -> Unit = {},
    setIsNewNote: (value: Boolean) -> Unit
    ) {
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        LazyColumn {
            items(uiState.noteList, key = { it.id }) { note ->
                Box(
                    modifier = modifier
                        .fillMaxSize()

                ) {
                    ListItem(
                        note = note,
                        onDelete = onNoteDelete,
                        navigateToNote = {
                            setIsNewNote(false)
                            onNavigateToNoteCreation(it)
                        }
                    )
                }
            }
        }
        Box(
            modifier = modifier
                .align(Alignment.BottomEnd)
        ) {
            FAB(
                onClick = {
                    setIsNewNote(true)
                    onNavigateToNoteCreation(it)

                }
            )
        }
    }
}