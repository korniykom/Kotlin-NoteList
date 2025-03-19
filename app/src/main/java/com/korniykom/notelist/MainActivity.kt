package com.korniykom.notelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korniykom.notelist.ui.screen.CreateNoteScreen
import com.korniykom.notelist.ui.screen.HomeScreen
import com.korniykom.notelist.ui.theme.BackgroundColor
import com.korniykom.notelist.ui.theme.NoteListTheme
import com.korniykom.notelist.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

enum class NoteScreen {
    Home, CreateNote, EditNote
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: NoteViewModel = hiltViewModel()
            val navController = rememberNavController()
            NoteListTheme {
                NavHost(
                    modifier = Modifier
                        .background(BackgroundColor)
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars),
                    navController = navController,
                    startDestination = NoteScreen.Home.name
                ) {
                    composable(route = NoteScreen.Home.name) {
                        HomeScreen(
                            viewModel = viewModel,
                            onNavigateToNoteCreation = {
                                navController.navigate(NoteScreen.CreateNote.name)
                            },
                            onNoteDelete = {
                                viewModel.removeNote(it)
                            },
                        )
                    }
                    composable(route = NoteScreen.CreateNote.name) {
                        CreateNoteScreen(
                            viewModel = viewModel,
                            onNoteSave = {
                                viewModel.addNote(it)
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}
