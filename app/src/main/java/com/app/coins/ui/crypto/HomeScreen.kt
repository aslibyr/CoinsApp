package com.app.coins.ui.crypto

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Text(text = "ekran 1")

}