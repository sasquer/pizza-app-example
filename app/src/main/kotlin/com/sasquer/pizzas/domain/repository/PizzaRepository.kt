package com.sasquer.pizzas.domain.repository

import com.sasquer.pizzas.domain.model.Pizza
import kotlinx.coroutines.flow.Flow

interface PizzaRepository {
    fun getPizzas(): Flow<List<Pizza>>
    suspend fun refreshPizzas()
}
