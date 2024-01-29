package com.app.coins.domain.use_case

import com.app.coins.data.model.ChartsResponse
import com.app.coins.data.remote.repository.CoinsRepository
import com.app.coins.domain.BaseUIModel
import com.app.coins.domain.GenericError
import com.app.coins.domain.Loading
import com.app.coins.domain.Success
import com.app.coins.domain.mapper.toUIModel
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinChartsUseCase @Inject constructor(
    private val repository: CoinsRepository
) {
    operator fun invoke(coinId: String): Flow<BaseUIModel<List<ChartsResponse>>> {
        return flow {
            emit(Loading())
            emit(
                when (val response = repository.getCoinCharts(coinId)) {
                    is ResultWrapper.GenericError -> {
                        GenericError(errorMessage = response.error.toString())
                    }

                    ResultWrapper.Loading -> {
                        Loading()
                    }

                    ResultWrapper.NetworkError -> {
                        GenericError(errorMessage = "Bir hata ile karşılaşıldı")
                    }

                    is ResultWrapper.Success -> {
                        Success(response.value)
                    }
                }
            )
        }
    }
}