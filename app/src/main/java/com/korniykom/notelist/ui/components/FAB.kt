package com.korniykom.notelist.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.korniykom.notelist.R
import com.korniykom.notelist.ui.theme.Aqua
import com.korniykom.notelist.ui.theme.Black
import com.korniykom.notelist.ui.theme.DarkBlue
import com.korniykom.notelist.ui.theme.DarkPurple
import com.korniykom.notelist.ui.theme.Green
import com.korniykom.notelist.ui.theme.Magenta
import com.korniykom.notelist.ui.theme.Orange
import com.korniykom.notelist.ui.theme.PinkyPurple
import com.korniykom.notelist.ui.theme.Purple
import com.korniykom.notelist.ui.theme.Red
import com.korniykom.notelist.ui.theme.White
import com.korniykom.notelist.ui.theme.Yellow

@Composable
fun FAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
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
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
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

    FloatingActionButton(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .size(80.dp)
            .rotate(rotationAngle)
            .clip(CircleShape)
            .border(
                color = borderColor,
                width = 2.dp,
                shape = CircleShape
            ),
        onClick = onClick,
        containerColor = buttonColor,

    ) {
        Icon(
            modifier = modifier
                .size(50.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add Note",
            tint = borderColor,
        )
    }
}