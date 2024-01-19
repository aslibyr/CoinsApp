package com.app.coins.data.remote.dataSource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.paging.CoinsPagingSource
import com.app.coins.data.remote.webservice.WebService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinPagingDataSource @Inject constructor(private val webService: WebService) {
    fun getCoins(query: String): Flow<PagingData<CryptoResponse>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { CoinsPagingSource(webService = webService, query = query) }
    ).flow


}