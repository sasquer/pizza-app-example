package com.sasquer.pizzas.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sasquer.pizzas.domain.repository.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PizzaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                repository.refreshPizzas()
                _uiState.value = MainUiState.Success
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Failed to load pizzas")
            }
        }
    }

    fun retry() = loadPizzas()
}
