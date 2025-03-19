package com.korniykom.notelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.korniykom.notelist.ui.HomeScreen
import com.korniykom.notelist.ui.theme.NoteListTheme
import com.korniykom.notelist.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: NoteViewModel = hiltViewModel()
            NoteListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}
