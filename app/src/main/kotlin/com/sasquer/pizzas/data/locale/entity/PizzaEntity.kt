package com.sasquer.pizzas.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sasquer.pizzas.domain.model.Pizza
import com.sasquer.pizzas.domain.model.PizzaVariant

@Entity(tableName = "pizzas")
data class PizzaEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val variantsJson: String,// stored as JSON — see [PizzaTypeConverters]
    val defaultSize: String
) {
    fun toDomain(variants: List<PizzaVariant>): Pizza = Pizza(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        variants = variants,
        defaultSize = defaultSize
    )

    companion object {
        fun fromDomain(pizza: Pizza, variantsJson: String) = PizzaEntity(
            id = pizza.id,
            name = pizza.name,
            description = pizza.description,
            imageUrl = pizza.imageUrl,
            variantsJson = variantsJson,
            defaultSize = pizza.defaultSize
        )
    }
}
