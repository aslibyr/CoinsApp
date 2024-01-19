package com.app.coins.domain

import androidx.paging.PagingData
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinsRepository) {
    operator fun invoke(query: String): Flow<PagingData<CryptoResponse>> =
        repository.getCoins(query)

}