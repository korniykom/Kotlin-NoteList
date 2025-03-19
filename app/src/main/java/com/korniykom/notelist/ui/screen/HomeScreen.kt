package com.korniykom.notelist.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.korniykom.notelist.ui.components.FAB

import com.korniykom.notelist.ui.viewmodel.NoteViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
    onNavigateToNoteCreation: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        Box(
            modifier = modifier
                .align(Alignment.BottomEnd)
        ) {
            FAB(
                onClick = onNavigateToNoteCreation
            )
        }
    }
}