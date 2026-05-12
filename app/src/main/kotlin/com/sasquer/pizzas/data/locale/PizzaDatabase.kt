package com.sasquer.pizzas.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sasquer.pizzas.data.locale.converter.PizzaTypeConverters
import com.sasquer.pizzas.data.locale.entity.PizzaEntity

@Database(
    entities = [PizzaEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(PizzaTypeConverters::class)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao
}
