package com.app.coins.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.data.model.ResultItem
import com.app.coins.utils.ScreenRoutes
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel = hiltViewModel(), newsClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        LoadingDialog()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryBackgroundColor, secondaryBackgroundColor
                    )
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState.newsData?.result?.let { list ->
            items(list.filterNotNull()) { item ->
                NewsItem(news = item, onItemClick = {
                    val route = ScreenRoutes.NEWS_DETAIL_SCREEN_ROUTE.replace(
                        oldValue = "{id}",
                        newValue = item.id.toString()
                    )
                    newsClicked(route)
                })
            }
        }
    }
}

@Composable
fun NewsItem(news: ResultItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = secondaryBackgroundColor)

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)
        ) {
            news.title?.let { Text(text = it) }
        }
    }
}