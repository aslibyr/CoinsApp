package com.app.coins.ui.crypto

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.buttons.ListResetButton
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.utils.PriceFormatterUtil
import com.app.coins.utils.ScreenRoutes
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.darkTextColor
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun CryptoScreen(
    viewModel: CryptoScreenViewModel = hiltViewModel(),
    cryptoClicked: (String) -> Unit
) {
    val coinsPagingItems: LazyPagingItems<CryptoUIModel> =
        viewModel.coinsState.collectAsLazyPagingItems()

    var queryText by rememberSaveable {
        mutableStateOf("")
    }
    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var isScrollButtonVisible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest {
                if (it != null && isScrollButtonVisible != listState.firstVisibleItemIndex > 0) {
                    isScrollButtonVisible = listState.firstVisibleItemIndex > 0
                }
            }
    }


    Box(
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
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
        if (isScrollButtonVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 100.dp)
            ) {
                ListResetButton {
                    scope.launch {
                        isScrollButtonVisible = false
                        listState.animateScrollToItem(0)
                    }
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
fun CryptoListItem(coin: CryptoUIModel, onItemClick: () -> Unit) {
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
                    .clip(CircleShape)
                    .weight(1f),
                model = coin.icon,
                contentDescription = "",
                placeholder = painterResource(
                    id = R.drawable.ic_launcher_background
                ),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            coin.name?.let {
                Text(
                    text = it,
                    color = darkTextColor,
                    modifier = Modifier.weight(3f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                horizontalAlignment = Alignment.Start
            ) {
                coin.priceChange1d?.let {
                    Text(
                        text = "%${it}",
                        fontSize = 14.sp,
                        fontFamily = FontType.quicksandMedium,
                        color = if (it.contains("-")) Color.Red else Color.Green,
                        modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 2.dp)
                    )
                }

                coin.price?.let { price ->
                    val formattedPrice = PriceFormatterUtil.formatPrice(price)
                    Text(
                        text = "$formattedPrice $ ",
                        fontSize = 14.sp,
                        fontFamily = FontType.quicksandLight,
                        color = darkTextColor,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    }
}