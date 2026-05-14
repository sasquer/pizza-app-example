package com.sasquer.pizzas.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sasquer.pizzas.ui.helper.defaultShadow

@Composable
fun PizzaDetailBottomBar(
    quantity: Int,
    price: Double,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .background(Color(0xFFE8DAD2), RoundedCornerShape(50))
        ) {
            QuantityButton(label = "−", onClick = onDecrease)

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(30.dp)
            ) {

                Text(
                    text = quantity.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                )
            }

            QuantityButton(label = "+", onClick = onIncrease)

        }

        Text(
            text = "$${String.format("%.2f", price)}",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
        )

        Button(
            onClick = { },
            shape = RoundedCornerShape(36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF19C4EA)
            ),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),

        ) {
            Text("Add", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)

        }
    }
}


@Composable
private fun QuantityButton(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
//            .clip(CircleShape)
            .defaultShadow()
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontSize = 20.sp, color = Color.Black)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 480)
@Composable
fun PizzaDetailBottomBarPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        PizzaDetailBottomBar(
            quantity = 1,
            price = 14.99,
            onDecrease = {},
            onIncrease = {},
        )
    }
}
