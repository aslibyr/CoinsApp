package com.app.coins.data.remote.repository

import androidx.paging.PagingData
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.dataSource.CoinPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val dataSource: CoinPagingDataSource
) {
    fun getCoins(query: String): Flow<PagingData<CryptoResponse>> =
        dataSource.getCoins(query = query)
}