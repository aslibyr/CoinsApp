package com.app.coins.ui.crypto.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.coins.custom.top_bar.TopBarComponentUIModel
import com.app.coins.custom.top_bar.TopBarView
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun DetailScreen(viewModel: DetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryBackgroundColor, secondaryBackgroundColor
                    )
                )
            )
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarView(
                model = TopBarComponentUIModel(
                    title = "Detail",
                    shouldShowBackIcon = true,
                ),
                onBackClick = { onBackClick() },
            )
        }
    }
}