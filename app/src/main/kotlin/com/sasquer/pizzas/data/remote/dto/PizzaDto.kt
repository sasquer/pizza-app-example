package com.sasquer.pizzas.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sasquer.pizzas.domain.model.Pizza
import com.sasquer.pizzas.domain.model.PizzaVariant

data class PizzaDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("variants") val variants: List<PizzaVariantDto>,
    @SerializedName("default_size") val defaultSize: String
) {
    fun toDomain(): Pizza = Pizza(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        variants = variants.map { PizzaVariant(size = it.size, price = it.price) },
        defaultSize = defaultSize,
    )
}
