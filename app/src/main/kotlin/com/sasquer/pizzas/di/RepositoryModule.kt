package com.sasquer.pizzas.di

import com.sasquer.pizzas.data.repository.PizzaRepositoryImpl
import com.sasquer.pizzas.domain.repository.PizzaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPizzaRepository(
        impl: PizzaRepositoryImpl
    ): PizzaRepository
}
