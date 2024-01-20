package com.app.coins.ui.crypto

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.coins.data.model.CryptoResponse
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val coinsPagingItems: LazyPagingItems<CryptoResponse> =
        viewModel.coinsState.collectAsLazyPagingItems()

    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryBackgroundColor),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(coinsPagingItems.itemCount) { index ->

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Gray)
                ) {
                    Text(
                        text = coinsPagingItems[index]!!.name.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        coinsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        CircularProgressIndicator()

                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = coinsPagingItems.loadState.refresh as LoadState.Error

                }

                loadState.append is LoadState.Loading -> {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = coinsPagingItems.loadState.append as LoadState.Error

                    // handle error

                }
            }
        }
    }
}