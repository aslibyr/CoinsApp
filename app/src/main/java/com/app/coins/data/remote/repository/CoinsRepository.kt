package com.app.coins.data.remote.repository

import androidx.paging.PagingData
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.dataSource.CoinPagingDataSource
import com.app.coins.data.remote.dataSource.CoinRemoteDataSource
import com.app.coins.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val dataSource: CoinPagingDataSource,
    private val remoteDataSource: CoinRemoteDataSource
) {
    fun getCoins(query: String): Flow<PagingData<CryptoResponse>> =
        dataSource.getCoins(query = query)
    suspend fun getCoinDetail(coinId : String) : ResultWrapper<CryptoResponse> = remoteDataSource.getCoinDetail(coinId)
}