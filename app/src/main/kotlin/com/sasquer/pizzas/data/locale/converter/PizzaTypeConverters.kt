package com.sasquer.pizzas.data.locale.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sasquer.pizzas.domain.model.PizzaVariant

class PizzaTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromVariantList(variants: List<PizzaVariant>): String = gson.toJson(variants)

    @TypeConverter
    fun toVariantList(json: String): List<PizzaVariant> {
        val type = object : TypeToken<List<PizzaVariant>>() {}.type
        return gson.fromJson(json, type)
    }
}
