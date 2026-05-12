package com.sasquer.pizzas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sasquer.pizzas.ui.navigation.PizzaNavGraph
import com.sasquer.pizzas.ui.theme.PizzasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzasTheme {
                PizzaNavGraph()
            }
        }
    }
}
