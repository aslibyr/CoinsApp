package com.app.coins.data.remote.dataSource

import com.app.coins.data.model.ChartsResponse
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.webservice.WebService
import com.app.coins.utils.ResultWrapper
import com.app.coins.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoinRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCoinDetail(coinId : String) : ResultWrapper<CryptoResponse> = safeApiCall(Dispatchers.IO) { webService.getCoinDetail(coinId)}
    suspend fun getCoinCharts(coinId : String) : ResultWrapper<List<ChartsResponse>> = safeApiCall(Dispatchers.IO) { webService.getCoinCharts(coinId)}
}