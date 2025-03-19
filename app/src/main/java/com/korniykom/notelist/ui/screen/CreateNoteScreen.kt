package com.korniykom.notelist.ui.screen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.ui.theme.Aqua
import com.korniykom.notelist.ui.theme.DarkBlue
import com.korniykom.notelist.ui.theme.DarkPurple
import com.korniykom.notelist.ui.theme.Magenta
import com.korniykom.notelist.ui.theme.Purple
import com.korniykom.notelist.ui.theme.Yellow
import com.korniykom.notelist.ui.viewmodel.NoteViewModel

@Composable
fun CreateNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
    onNoteSave: (note: Note) -> Unit = {}
) {
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }

    val infiniteTransition = rememberInfiniteTransition()

    val buttonColor by infiniteTransition.animateColor(
        initialValue = DarkPurple,
        targetValue = Yellow,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                DarkPurple at 0
                Purple at 3000
            },
            repeatMode = RepeatMode.Reverse
        ),
    )

    val borderColor by infiniteTransition.animateColor(
        initialValue = Aqua,
        targetValue = Magenta,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4000
                Aqua at 1000
                DarkBlue at 2000
                Magenta at 3000
            },
            repeatMode = RepeatMode.Reverse
        ),
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Note Title") }
        )
        Spacer(modifier = modifier.size(32.dp))
        TextField(
            value = contentInput,
            onValueChange = { contentInput = it },
            label = { Text("Note Content") }
        )
        Spacer(modifier = modifier.size(32.dp))
        Button(
            modifier = modifier
                .size(140.dp, 60.dp)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                if(titleInput != "" && contentInput != "") {
                    onNoteSave(Note(title = titleInput, content = contentInput))
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = borderColor
            )
        ) {
            Text("Save Note")
        }
    }
}