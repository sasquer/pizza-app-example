package com.sasquer.pizzas.di

import android.content.Context
import androidx.room.Room
import com.sasquer.pizzas.data.locale.PizzaDao
import com.sasquer.pizzas.data.locale.PizzaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PizzaDatabase =
        Room.databaseBuilder(context, PizzaDatabase::class.java, "pizza_db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()

    @Provides
    fun providePizzaDao(db: PizzaDatabase): PizzaDao = db.pizzaDao()
}
