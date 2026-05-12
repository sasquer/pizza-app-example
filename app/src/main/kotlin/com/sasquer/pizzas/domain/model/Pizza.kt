package com.sasquer.pizzas.domain.model

data class Pizza(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val variants: List<PizzaVariant>,
    val defaultSize: String,
) {
    val defaultPrice: Double
        get() = variants.firstOrNull { it.size == defaultSize }?.price
            ?: variants.firstOrNull()?.price
            ?: 0.0
}

data class PizzaVariant(
    val size: String,
    val price: Double,
)
