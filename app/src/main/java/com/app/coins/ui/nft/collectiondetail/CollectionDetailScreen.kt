package com.app.coins.ui.nft.collectiondetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.coins.ui.nft.NftCollectionScreenViewModel

@Composable
fun CollectionDetailScreen(
    viewModel: NftCollectionScreenViewModel = hiltViewModel(),
) {
    Text(text = viewModel.collectionAddresses[0])
}