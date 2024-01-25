package com.app.coins.ui.crypto.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.coins.custom.loading.LoadingDialog
import com.app.coins.custom.top_bar.TopBarComponentUIModel
import com.app.coins.custom.top_bar.TopBarView
import com.app.coins.domain.Empty
import com.app.coins.domain.GenericError
import com.app.coins.domain.Loading
import com.app.coins.domain.Success
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.secondaryBackgroundColor

@Composable
fun DetailScreen(
    viewModel: CryptoDetailScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val coinDetail by viewModel.coinDetailState.collectAsStateWithLifecycle()


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
                title = "Detail",
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
    crypto : CryptoUIModel
) {

    crypto.name?.let { Text(text = it) }
}
