package com.sasquer.pizzas.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sasquer.pizzas.domain.model.PizzaVariant
import com.sasquer.pizzas.ui.helper.defaultShadow

@Composable
fun PizzaDetailSizeSelector(
    variants: List<PizzaVariant>,
    selectedVariant: PizzaVariant?,
    onVariantSelected: (PizzaVariant) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        variants.forEach { variant ->
            val isSelected = variant.size == selectedVariant?.size

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .defaultShadow()
                    .background(if (isSelected) Color.Black else Color.White)
                    .clickable { onVariantSelected(variant) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = variant.size,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}
