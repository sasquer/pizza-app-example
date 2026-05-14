package com.sasquer.pizzas.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object PizzaDetail : Screen("pizzaDetail?startIndex={startIndex}") {
        fun createRoute(startIndex: Int = 0) = "pizzaDetail?startIndex=$startIndex"
    }
}
