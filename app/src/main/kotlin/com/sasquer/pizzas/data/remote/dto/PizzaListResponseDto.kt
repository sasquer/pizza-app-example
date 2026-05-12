package com.sasquer.pizzas.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PizzaListResponseDto(
    @SerializedName("pizzas") val pizzas: List<PizzaDto>
)
