package com.sasquer.pizzas.data.remote

import com.sasquer.pizzas.domain.model.Pizza
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: PizzaApiService
) {
    suspend fun fetchPizzas(): List<Pizza> = api.getPizzas().pizzas.map { it.toDomain() }
}
