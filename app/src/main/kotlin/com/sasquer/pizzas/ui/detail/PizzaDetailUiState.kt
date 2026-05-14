package com.sasquer.pizzas.ui.detail

import com.sasquer.pizzas.domain.model.Pizza
import com.sasquer.pizzas.domain.model.PizzaVariant

data class PizzaDetailUiState(
    val pizzas: List<Pizza> = emptyList(),
    val currentIndex: Int = 1,
    val selectedVariant: PizzaVariant? = null,
    val quantity: Int = 1,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val currentPizza: Pizza? get() = pizzas.getOrNull(currentIndex)
    val currentPrice: Double
        get() = (selectedVariant?.price ?: currentPizza?.defaultPrice ?: 0.0) * quantity
}
