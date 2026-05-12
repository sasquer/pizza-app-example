package com.sasquer.pizzas.ui.splash

sealed class SplashUiState {
    object Idle : SplashUiState()
    object Loading : SplashUiState()
    object Success : SplashUiState()
    data class Error(val message: String) : SplashUiState()
}
