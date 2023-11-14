package com.aliceresponde.aristiinstagramcompose

import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ChangeColorBasic() {
    var isOriginalColor by rememberSaveable { mutableStateOf(true) }
    val color = if (isOriginalColor) Color.Red else Color.Green
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(color)
            .clickable { isOriginalColor = !isOriginalColor }
    )
}

@Composable
fun AnimateColorBasic() {
    var isOriginalColor by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current
    val color by animateColorAsState(
        targetValue = if (isOriginalColor) Color.Red else Color.Green,
        animationSpec = tween(3000),
        finishedListener = { Toast.makeText(context, "Animation finished", Toast.LENGTH_SHORT).show() },
        label = ""
    )

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(color)
            .clickable { isOriginalColor = !isOriginalColor }
    )
}

@Composable
fun TransitionColor() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(animation = tween(2000), repeatMode = RepeatMode.Reverse), label = ""
    )
    Box(
        modifier = Modifier.width(100.dp)
            .height(100.dp).background(color)
    )
}