package com.korniykom.notelist.ui.components

import androidx.annotation.DimenRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.korniykom.notelist.R
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.ui.theme.Aqua
import com.korniykom.notelist.ui.theme.DarkBlue
import com.korniykom.notelist.ui.theme.DarkPurple
import com.korniykom.notelist.ui.theme.Magenta
import com.korniykom.notelist.ui.theme.PinkyPurple
import com.korniykom.notelist.ui.theme.Purple
import com.korniykom.notelist.ui.theme.Red
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDelete: (note: Note) -> Unit,
    navigateToNote: (note: Note) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val borderColor by infiniteTransition.animateColor(
        initialValue = Aqua,
        targetValue = Magenta,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4000
                Aqua at 1000
                DarkBlue at 2000
                Magenta at 3000
            }, repeatMode = RepeatMode.Reverse
        ),
    )
    val bounce by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) {
        isVisible = true
    }
    AnimatedVisibility(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.padding_small)
            ),
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { it / 3 }),
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 })
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(2.dp, borderColor), shape = RoundedCornerShape(12.dp)
                ), shape = RoundedCornerShape(12.dp),
            onClick = {
                navigateToNote(note)
            }
        ) {
            Row(
                modifier = modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${note.title}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Button(
                    modifier = modifier
                        .offset(y = bounce.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkPurple
                    ),
                    onClick = {
                        showDialog = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Delete note",
                        tint = PinkyPurple
                    )
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Do you want to delete this note?") },
            text = { Text("The action is undoable. Do you really want to delete this note?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        isVisible = false
                        onDelete(note)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Yes",
                        tint = PinkyPurple
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "No",
                        tint = PinkyPurple
                    )
                }
            }
        )
    }
}
