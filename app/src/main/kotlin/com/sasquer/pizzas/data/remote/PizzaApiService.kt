package com.sasquer.pizzas.data.remote

import com.sasquer.pizzas.data.remote.dto.PizzaListResponseDto
import retrofit2.http.GET

interface PizzaApiService {
    @GET("pizzas")
    suspend fun getPizzas(): PizzaListResponseDto
}
