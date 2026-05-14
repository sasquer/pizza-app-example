package com.sasquer.pizzas.ui.helper

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.defaultShadow(
    elevation: Dp = 2.dp,
    shape: Shape = CircleShape,
) = this.shadow(
    elevation = elevation,
    shape = shape,
)
