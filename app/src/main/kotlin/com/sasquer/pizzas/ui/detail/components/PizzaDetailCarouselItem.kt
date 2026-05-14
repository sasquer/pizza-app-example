package com.sasquer.pizzas.ui.detail.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sasquer.pizzas.R
import com.sasquer.pizzas.domain.model.Pizza

@Composable
fun PizzaDetailCarouselItem(
    pizza: Pizza?,
    selectedSize: String?,
    isCurrentPage: Boolean,
    onZoomClick: () -> Unit,
    onSideItemClick: () -> Unit,
) {
    val targetImageScale = when (selectedSize) {
        "S" -> 0.9f
        "M" -> 1.1f
        else -> 1.3f
    }

    val imageScale by animateFloatAsState(
        targetValue = targetImageScale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "pizzaScale"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = !isCurrentPage,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { onSideItemClick() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = pizza?.imageUrl,
            contentDescription = pizza?.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(260.dp)
                .scale(imageScale)
                .clip(CircleShape)
                .pointerInput(Unit) {
                    awaitEachGesture {
                        var zoom = 1f
                        awaitFirstDown(requireUnconsumed = false)
                        do {
                            val event = awaitPointerEvent()
                            zoom *= event.calculateZoom()
                            if (zoom > 1.15f) {
                                onZoomClick()
                                break
                            }
                        } while (event.changes.any { it.pressed })
                    }
                }
        )

        if (isCurrentPage) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onZoomClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.blur_round),
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .align(Alignment.Center)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_zoom_in),
                    contentDescription = "Zoom in",
                    tint = Color.White,
                    modifier = Modifier.size(42.dp)
                )
            }
        }
    }

}
