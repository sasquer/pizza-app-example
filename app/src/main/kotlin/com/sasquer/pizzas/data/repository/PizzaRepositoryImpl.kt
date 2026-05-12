package com.sasquer.pizzas.data.repository

import com.sasquer.pizzas.data.locale.PizzaDao
import com.sasquer.pizzas.data.locale.converter.PizzaTypeConverters
import com.sasquer.pizzas.data.locale.entity.PizzaEntity
import com.sasquer.pizzas.data.remote.RemoteDataSource
import com.sasquer.pizzas.domain.model.Pizza
import com.sasquer.pizzas.domain.repository.PizzaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PizzaRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val dao: PizzaDao
) : PizzaRepository {
    private val converter = PizzaTypeConverters()

    override fun getPizzas(): Flow<List<Pizza>> =
        dao.observeAll().map { entities ->
            entities.map { entity ->
                val variants = converter.toVariantList(entity.variantsJson)
                entity.toDomain(variants)
            }
        }

    override suspend fun refreshPizzas() {
        val pizzas = remote.fetchPizzas()
        dao.clearAll()
        dao.upsertAll(
            pizzas.map { pizza ->
                val variantsJson = converter.fromVariantList(pizza.variants)
                PizzaEntity.fromDomain(pizza, variantsJson)
            }
        )
    }
}
