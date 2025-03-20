package com.korniykom.notelist.ui.components

import androidx.annotation.DimenRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.korniykom.notelist.R
import com.korniykom.notelist.data.Note
import com.korniykom.notelist.ui.theme.Aqua
import com.korniykom.notelist.ui.theme.DarkBlue
import com.korniykom.notelist.ui.theme.DarkPurple
import com.korniykom.notelist.ui.theme.Magenta
import com.korniykom.notelist.ui.theme.Purple
import com.korniykom.notelist.ui.theme.Red
import kotlinx.coroutines.delay

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDelete: (note: Note) -> Unit,
    navigateToNote: (note: Note) ->  Unit
) {
    var isVisible by remember { mutableStateOf(false) }
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
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Title: ${note.title}"
                    )
                    Text(
                        text = "Content: ${note.content}"
                    )
                    Text(
                        text = "Created at: ${note.createdAt}"
                    )

                }

                Button(
                    modifier = modifier
                        .border(
                            BorderStroke(2.dp, borderColor),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkPurple
                    ),
                    onClick = {
                        isVisible = false
                        onDelete(note)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Delete note",
                        tint = Purple
                    )
                }
            }
        }
    }
}