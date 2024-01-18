package com.app.coins.ui.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = hiltViewModel()
) {
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
    ) {

    }

}
