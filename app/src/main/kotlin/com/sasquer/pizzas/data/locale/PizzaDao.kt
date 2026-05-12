package com.sasquer.pizzas.data.locale

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sasquer.pizzas.data.locale.entity.PizzaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {
    @Query("SELECT * FROM pizzas")
    fun observeAll(): Flow<List<PizzaEntity>>

    @Query("SELECT * FROM pizzas WHERE id = :id")
    suspend fun findById(id: String): PizzaEntity?

    @Upsert
    suspend fun upsertAll(pizzas: List<PizzaEntity>)

    @Query("DELETE FROM pizzas")
    suspend fun clearAll()
}
