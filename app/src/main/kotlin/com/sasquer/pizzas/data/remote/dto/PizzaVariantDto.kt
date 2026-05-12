package com.sasquer.pizzas.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PizzaVariantDto(
    @SerializedName("size") val size: String,
    @SerializedName("price") val price: Double
)
