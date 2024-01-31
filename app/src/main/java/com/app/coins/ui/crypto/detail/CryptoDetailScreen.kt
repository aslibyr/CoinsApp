package com.app.coins.ui.crypto.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.coins.R
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.custom.top_bar.TopBarComponentUIModel
import com.app.coins.custom.top_bar.TopBarView
import com.app.coins.domain.Empty
import com.app.coins.domain.GenericError
import com.app.coins.domain.Loading
import com.app.coins.domain.Success
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.utils.PriceFormatterUtil
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.darkTextColor
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun DetailScreen(
    viewModel: CryptoDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val coinDetail by viewModel.coinDetailState.collectAsStateWithLifecycle()
    val coinCharts by viewModel.coinChartsState.collectAsStateWithLifecycle()

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

        when (coinDetail) {
            is Empty -> TODO()
            is GenericError -> {

            }

            is Loading -> {
                LoadingDialog()
            }

            is Success -> {
                val response = (coinDetail as Success<CryptoUIModel>).response
                StateLessCryptoDetail(response)
            }
        }

    }
}

@Composable
fun StateLessCryptoDetail(
    crypto: CryptoUIModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .padding(8.dp),
            model = crypto.icon,
            contentDescription = "",
            placeholder = painterResource(
                id = R.drawable.ic_launcher_background
            ),
            error = painterResource(id = R.drawable.ic_launcher_background)
        )
        crypto.name?.let {
            Text(
                text = it,
                color = light,
                fontSize = 18.sp,
                fontFamily = FontType.quicksandBold
            )
        }
        crypto.symbol?.let {
            Text(
                text = it,
                color = light,
                fontSize = 12.sp,
                fontFamily = FontType.quicksandLight
            )
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
            crypto.price?.let { price ->
                val formattedPrice = PriceFormatterUtil.formatPrice(price)
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

