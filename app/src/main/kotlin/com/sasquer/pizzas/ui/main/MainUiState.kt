package com.sasquer.pizzas.ui.main

sealed class MainUiState {
    object Loading : MainUiState()
    object Success : MainUiState()
    data class Error(val message: String) : MainUiState()
}
