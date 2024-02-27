package com.app.coins.ui.nft.collectiondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.data.model.AssetsDataItem
import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.utils.PriceFormatterUtil
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor
import com.app.coins.utils.theme.textColor

@Composable
fun CollectionDetailScreen(
    viewModel: CollectionDetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        if (uiState.isLoading) {
            LoadingDialog()
        }
        if (uiState.isSuccess) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                uiState.collectionData?.let { collectionData ->
                    uiState.assetsData?.let { assetsData ->
                        assetsData.data?.get(0)
                            ?.let { CollectionDetailItem(collectionData, it, onBackClick) }
                    }
                }
            }
        }
    }
}


@Composable
fun CollectionDetailItem(
    nft: CollectionDetailResponse, asset: AssetsDataItem, onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth()
                .blur(2.dp),
            model = nft.bannerImg,
            contentDescription = "",
            placeholder = painterResource(
                id = R.drawable.error
            ),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(60.dp))
                    .shadow(30.dp)
                    .height(100.dp)
                    .width(100.dp),
                model = nft.img,
                contentDescription = "",
                placeholder = painterResource(
                    id = R.drawable.error
                ),
                error = painterResource(id = R.drawable.error)
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(
                        light
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .background(secondaryBackgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    nft.oneDayAveragePrice?.let { price ->
                        val formattedPrice = PriceFormatterUtil.formatPrice(price.toString())
                        Text(
                            modifier = Modifier
                                .padding(start = 30.dp),
                            text = "$formattedPrice $ ",
                            fontSize = 24.sp,
                            fontFamily = FontType.quicksandBold,
                            color = textColor
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
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
                                modifier = Modifier.size(20.dp),
                                tint = textColor
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Language,
                            contentDescription = "",
                            tint = textColor,
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.twitter_icon),
                            contentDescription = "",
                            tint = textColor,
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.discord_icon),
                            contentDescription = "",
                            tint = textColor,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 8.dp)
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
                }
            }
        }
        Icon(imageVector = Icons.Filled.NavigateBefore,
            "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(38.dp)
                .padding(start = 16.dp, top = 16.dp)
                .clickable {
                    onBackClick()
                }
                .shadow(50.dp),
            tint = textColor)
    }
}

