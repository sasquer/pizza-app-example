package com.sasquer.pizzas.ui.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sasquer.pizzas.ui.detail.components.PizzaDetailBottomBar
import com.sasquer.pizzas.ui.detail.components.PizzaDetailCarouselItem
import com.sasquer.pizzas.ui.detail.components.PizzaDetailSizeSelector
import com.sasquer.pizzas.ui.detail.components.PizzaDetailZoomOverlay
import com.sasquer.pizzas.ui.detail.components.TopBar
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun PizzaDetailScreen(
    onBack: () -> Unit,
    viewModel: PizzaDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = uiState.currentIndex,
        pageCount = { uiState.pizzas.size }
    )
    var showZoom by remember { mutableStateOf(false) }
    var zoomImageUrl by remember { mutableStateOf("") }

    LaunchedEffect(pagerState.currentPage) {
        if (!pagerState.isScrollInProgress) {
            viewModel.onPageChanged(pagerState.currentPage)
        }
    }

    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            viewModel.onPageChanged(pagerState.currentPage)
        }
    }

    BackHandler(enabled = showZoom) {
        showZoom = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EBE4))
            .safeDrawingPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                title = uiState.currentPizza?.name ?: "",
                onBack = onBack,
                onLike = {},
            )
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 100.dp),
                beyondViewportPageCount = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) { page ->
                val pizza = uiState.pizzas.getOrNull(page)
                val isCurrentPage = page == pagerState.currentPage

                val pageOffset = (pagerState.currentPage - page) +
                        pagerState.currentPageOffsetFraction
                val fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                val cardScale = lerp(0.40f, 1f, fraction)

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .graphicsLayer { scaleX = cardScale; scaleY = cardScale },
                    contentAlignment = Alignment.Center
                ) {
                    PizzaDetailCarouselItem(
                        pizza = pizza,
                        selectedSize = if (isCurrentPage)
                            uiState.selectedVariant?.size else pizza?.defaultSize,
                        isCurrentPage = isCurrentPage,
                        onZoomClick = {
                            zoomImageUrl = pizza?.imageUrl ?: ""
                            showZoom = true
                        },
                        onSideItemClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    uiState.currentPizza?.let { pizza ->
                        PizzaDetailSizeSelector(
                            variants = pizza.variants,
                            selectedVariant = uiState.selectedVariant,
                            onVariantSelected = viewModel::onVariantSelected
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = uiState.currentPizza?.description ?: "",
                        fontSize = 16.sp,
                        color = Color(0xFF333333),
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

        }

        PizzaDetailBottomBar(
            quantity = uiState.quantity,
            price = uiState.currentPrice,
            onDecrease = { viewModel.onQuantityChange(-1) },
            onIncrease = { viewModel.onQuantityChange(1) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        if (showZoom) {
            PizzaDetailZoomOverlay(
                imageUrl = zoomImageUrl,
                onDismiss = { showZoom = false }
            )

        }

    }

}
