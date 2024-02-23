package com.app.coins.ui.nft.collectiondetail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CollectionDetailScreen(
    viewModel: CollectionDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        CircularProgressIndicator()
    }
    if (uiState.isSuccess) {
        uiState.collectionData?.let {

            Text(text = it?.name.toString())


        }
    }

}

