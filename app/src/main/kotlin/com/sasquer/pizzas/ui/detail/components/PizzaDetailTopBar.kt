package com.sasquer.pizzas.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sasquer.pizzas.R
import com.sasquer.pizzas.ui.helper.defaultShadow

@Composable
fun TopBar(title: String, onBack: () -> Unit, onLike: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(48.dp)
                .defaultShadow()
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
            )
        }

        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "Pizzas",
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        IconButton(
            onClick = onLike,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(48.dp)
                .defaultShadow()
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}
