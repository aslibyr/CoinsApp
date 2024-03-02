package com.app.coins.ui.news.newsdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.custom.top_bar.TopBarComponentUIModel
import com.app.coins.custom.top_bar.TopBarView
import com.app.coins.data.model.NewsDetailResponse
import com.app.coins.utils.openChrome
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
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
                TopBarView(
                    model = TopBarComponentUIModel(
                        title = "",
                        shouldShowBackIcon = true,
                        endIcon = if (uiState.newsDetailData?.sourceLink.isNullOrEmpty()) null else Icons.Filled.Link
                    ),
                    onBackClick = { onBackClick() },
                    onEndIconClick = {
                        uiState.newsDetailData?.sourceLink.let { url ->
                            url?.let { context.openChrome(it) }
                        }
                    }
                )
                uiState.newsDetailData?.let { newsData ->
                    NewsDetailUI(news = newsData)
                }
            }
        }
    }
}

@Composable
fun NewsDetailUI(news: NewsDetailResponse) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        news.imgUrl?.let { imageUrl ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                model = imageUrl, contentDescription = "",
                placeholder = painterResource(
                    id = R.drawable.error
                ),
                error = painterResource(id = R.drawable.error),
            )
        }

        news.title?.let {
            Text(text = it)
        }
        news.description?.let { Text(text = it) }

    }
}
