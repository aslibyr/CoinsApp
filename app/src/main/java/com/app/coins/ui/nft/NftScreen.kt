package com.app.coins.ui.nft

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.app.coins.R
import com.app.coins.data.model.DataItem
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor
import com.app.coins.utils.theme.textColor

@Composable
fun NftScreen(
    viewModel: NftScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        CircularProgressIndicator()
    }
    uiState.nftData?.let {
        Text(text = it.data?.get(0)?.name.toString())
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
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2)
        ) {
            uiState.nftData?.data?.let { list ->
                items(list) { item ->
                    if (item != null) {
                        NftListItem(nft = item) {

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NftListItem(nft: DataItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(220.dp)
            .clickable {
                onItemClick()
            },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(primaryBackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SubcomposeAsyncImage(modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
                model = nft.img.toString(),
                contentDescription = "",
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = ""
                    )
                }
            )
            Row(
                modifier = Modifier
                    .background(secondaryBackgroundColor)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                nft.name?.let {
                    Text(
                        text = it, fontFamily = FontType.quicksandMedium,
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