package com.sasquer.pizzas.domain.usecase

import com.sasquer.pizzas.domain.model.Pizza
import com.sasquer.pizzas.domain.repository.PizzaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPizzasUseCase @Inject constructor(
    private val repository: PizzaRepository
) {
    operator fun invoke(): Flow<List<Pizza>> = repository.getPizzas()
}
