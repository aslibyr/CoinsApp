package com.app.coins.ui.nft

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NftScreen(
    viewModel: NftScreenViewModel = hiltViewModel()
) {
    Text(text = "ekran 2")
}