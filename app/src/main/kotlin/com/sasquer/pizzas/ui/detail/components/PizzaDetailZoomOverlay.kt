package com.sasquer.pizzas.ui.detail.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PizzaDetailZoomOverlay(imageUrl: String, onDismiss: () -> Unit) {
    var entered by remember { mutableStateOf(false) }

    val entranceScale by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "zoomEntrance"
    )

    val bgAlpha by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = tween(220),
        label = "bgAlpha"
    )

    LaunchedEffect(Unit) { entered = true }

    var userZoom by remember { mutableStateOf(4f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = bgAlpha }
            .background(Color.Black)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            filterQuality = FilterQuality.High,
            contentDescription = "Zoomed pizza",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer {
                    scaleX = entranceScale * userZoom
                    scaleY = entranceScale * userZoom
                    translationX = offset.x
                    translationY = offset.y
                }
                .pointerInput(Unit) {
                    awaitEachGesture {
                        awaitFirstDown(requireUnconsumed = false)
                        do {
                            val event = awaitPointerEvent()
                            val zoomChange = event.calculateZoom()
                            val panChange = event.calculatePan()

                            userZoom = (userZoom * zoomChange).coerceIn(1.5f, 5f)
                            offset = Offset(
                                x = (offset.x + panChange.x).coerceIn(-200f, 200f),
                                y = (offset.y + panChange.y).coerceIn(-200f, 200f)
                            )

                            event.changes.forEach { it.consume() }
                        } while (event.changes.any { it.pressed })
                    }
                }
                .clickable(                indication = null,
                    interactionSource = remember { MutableInteractionSource() },) { onDismiss() }
        )

        Text(
            text = "Pinch to zoom · Tap to close",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .graphicsLayer { alpha = bgAlpha }
                .padding(bottom = 40.dp)
        )
    }

}
