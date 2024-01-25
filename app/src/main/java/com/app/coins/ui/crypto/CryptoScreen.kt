package com.app.coins.ui.crypto

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.custom.textfield.CustomOutlinedTextField
import com.app.coins.data.model.CryptoResponse
import com.app.coins.utils.ScreenRoutes
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun HomeScreen(
    viewModel: CryptoScreenViewModel = hiltViewModel(),
    cryptoClicked: (String) -> Unit
) {
    val coinsPagingItems: LazyPagingItems<CryptoResponse> =
        viewModel.coinsState.collectAsLazyPagingItems()

    var queryText by rememberSaveable {
        mutableStateOf("")
    }
    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)

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

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = "Ara",
            text = queryText,
            returnText = {
                queryText = it
            },
            onImeClicked = { },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(coinsPagingItems.itemCount) { index ->

                coinsPagingItems[index]?.let {
                    CryptoListItem(coin = it, onItemClick = {
                        val route = ScreenRoutes.CRYPTO_DETAIL_SCREEN_ROUTE.replace(
                            oldValue = "{id}",
                            newValue = it.id.toString()
                        )
                        cryptoClicked(route)
                    })
                }
            }
        }

        coinsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingDialog()
                }

                loadState.refresh is LoadState.Error -> {
                    val error = coinsPagingItems.loadState.refresh as LoadState.Error

                }

                loadState.append is LoadState.Loading -> {
                    LoadingDialog()
                }

                loadState.append is LoadState.Error -> {
                    val error = coinsPagingItems.loadState.append as LoadState.Error

                    // handle error

                }
            }
        }
    }
}

@Composable
fun CryptoListItem(coin: CryptoResponse, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onItemClick()
            },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = light)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                model = coin.icon,
                contentDescription = "",
                placeholder = painterResource(
                    id = R.drawable.ic_launcher_background
                ),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            coin.name?.let { Text(text = it) }
        }

    }
}