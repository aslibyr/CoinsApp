package com.app.coins.ui.nft.collectiondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.custom.top_bar.TopBarComponentUIModel
import com.app.coins.custom.top_bar.TopBarView
import com.app.coins.data.model.AssetsDataItem
import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.utils.PriceFormatterUtil
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.darkTextColor
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor
import com.app.coins.utils.theme.textColor

@Composable
fun CollectionDetailScreen(
    viewModel: CollectionDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        LoadingDialog()
    } else if (uiState.isSuccess) {
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
            TopBarView(
                model = TopBarComponentUIModel(
                    title = "",
                    shouldShowBackIcon = true,
                ),
                onBackClick = { onBackClick() },
            )

            uiState.collectionData?.let { collectionData ->
                uiState.assetsData?.let { assetsData ->
                    assetsData.data?.get(0)?.let { CollectionDetailItem(collectionData, it) }
                }
            }
        }
    }
}


@Composable
fun CollectionDetailItem(
    nft: CollectionDetailResponse,
    asset: AssetsDataItem
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .padding(8.dp),
            model = nft.img,
            contentDescription = "",
            placeholder = painterResource(
                id = R.drawable.error
            ),
            error = painterResource(id = R.drawable.error)
        )
        Row(Modifier.wrapContentHeight()) {
            nft.name?.let {
                Text(
                    text = it,
                    color = textColor,
                    fontSize = 20.sp,
                    fontFamily = FontType.quicksandBold
                )
            }
            if (nft.verified == true) {
                Icon(
                    Icons.Filled.Verified,
                    contentDescription = "Verified",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(16.dp)
                        .align(Alignment.CenterVertically),
                    tint = textColor
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Floor Price",
                        fontFamily = FontType.quicksandMedium,
                        color = textColor,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    nft.floorPriceUsd?.toString()?.let { price ->
                        val formattedPrice = PriceFormatterUtil.formatPrice(price)
                        Text(
                            text = "$formattedPrice $ ",
                            color = textColor,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Total Volume",
                        fontFamily = FontType.quicksandMedium,
                        color = textColor,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    nft.totalVolume?.toString()?.let { volume ->
                        val formattedPrice = PriceFormatterUtil.formatPrice(volume)
                        Text(
                            text = "${formattedPrice} ",
                            color = textColor,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(
                    light
                )
        ) {
            nft.oneDayAveragePrice?.let { price ->
                val formattedPrice = PriceFormatterUtil.formatPrice(price.toString())
                Text(
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "$formattedPrice $ ",
                    fontSize = 24.sp,
                    fontFamily = FontType.quicksandBold,
                    color = darkTextColor
                )
            }
        }
    }
}

