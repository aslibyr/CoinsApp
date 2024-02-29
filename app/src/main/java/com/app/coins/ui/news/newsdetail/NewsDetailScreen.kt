package com.app.coins.ui.news.newsdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.data.model.ResultItem
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
        if (uiState.isLoading) {
            LoadingDialog()
        }
        if (uiState.isSuccess) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                uiState.newsDetailData?.let { newsData ->
                    NewsDetailUI(news = newsData) {
                        onBackClick()
                    }
                }
                uiState.newsDetailData?.title?.let { Text(text = it) }
            }
        }
    }

}

@Composable
fun NewsDetailUI(news: ResultItem, onBackClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        news.imgUrl?.let { imageUrl ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = imageUrl, contentDescription = "", placeholder = painterResource(
                    id = R.drawable.error
                ), error = painterResource(id = R.drawable.error)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            news.title?.let {
                Text(text = it)
            }
        }
    }
}
