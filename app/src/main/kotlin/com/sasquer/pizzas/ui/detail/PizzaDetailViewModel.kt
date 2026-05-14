package com.sasquer.pizzas.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sasquer.pizzas.domain.model.PizzaVariant
import com.sasquer.pizzas.domain.usecase.GetPizzasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaDetailViewModel @Inject constructor(
    private val getPizzasUseCase: GetPizzasUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val startIndex: Int = savedStateHandle["startIndex"] ?: 0

    private val _uiState = MutableStateFlow(PizzaDetailUiState())
    val uiState: StateFlow<PizzaDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getPizzasUseCase().collect { pizzas ->
                val defaultVariant = pizzas.getOrNull(startIndex)?.let { pizza ->
                    pizza.variants.firstOrNull { it.size == pizza.defaultSize }
                        ?: pizza.variants.firstOrNull()
                }
                _uiState.update {
                    it.copy(
                        pizzas = pizzas,
                        currentIndex = startIndex,
                        selectedVariant = defaultVariant,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onPageChanged(index: Int) {
        val pizza = _uiState.value.pizzas.getOrNull(index) ?: return
        val defaultVariant = pizza.variants.firstOrNull { it.size == pizza.defaultSize }
            ?: pizza.variants.firstOrNull()
        _uiState.update {
            it.copy(currentIndex = index, selectedVariant = defaultVariant, quantity = 1)
        }
    }

    fun onVariantSelected(variant: PizzaVariant) {
        _uiState.update { it.copy(selectedVariant = variant) }
    }

    fun onQuantityChange(delta: Int) {
        _uiState.update { it.copy(quantity = (it.quantity + delta).coerceIn(1, 99)) }
    }

}
