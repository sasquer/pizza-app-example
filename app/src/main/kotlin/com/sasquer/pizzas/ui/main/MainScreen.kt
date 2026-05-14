package com.sasquer.pizzas.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(
    onReadyToNavigate: (startIndex: Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is MainUiState.Success) {
            onReadyToNavigate(1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8DAD2)),
        contentAlignment = Alignment.Center
    ) {
        if (uiState is MainUiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(52.dp),
                color = Color(0xFFE74600),
                strokeWidth = 2.dp
            )
        }
    }
}
