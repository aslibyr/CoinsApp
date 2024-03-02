package com.app.coins.ui.news

import android.text.format.DateUtils.isToday
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.data.model.ResultItem
import com.app.coins.utils.DateFormatter
import com.app.coins.utils.ScreenRoutes
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.darkTextColor
import com.app.coins.utils.theme.light
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
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = light)

    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            news.imgUrl?.let { imageUrl ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(230.dp)
                        .shadow(3.dp)
                        .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    model = imageUrl, contentDescription = "", placeholder = painterResource(
                        id = R.drawable.error
                    ), error = painterResource(id = R.drawable.error),
                    contentScale = ContentScale.Crop
                )
            }
            news.title?.let {
                Text(
                    text = it,
                    color = darkTextColor,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            news.feedDate?.let { timestamp ->
                val formattedDate = if (isToday(timestamp)) {
                    DateFormatter.format(timestamp)
                } else {
                    DateFormatter.format(timestamp)
                }
                Text(
                    text = formattedDate,
                    fontFamily = FontType.quicksandLight
                )
            }
        }
    }
}