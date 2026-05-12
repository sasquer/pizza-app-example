package com.sasquer.pizzas.ui.navigation

sealed class Screen(val route: String) {
    object Splash   : Screen("splash")
    object PizzaList : Screen("pizza_list")
}
