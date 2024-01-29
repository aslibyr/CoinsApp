package com.app.coins.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.repository.CoinsRepository
import com.app.coins.domain.mapper.toUIModel
import com.app.coins.domain.model.CryptoUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinsRepository) {
    operator fun invoke(query: String): Flow<PagingData<CryptoUIModel>> =
        repository.getCoins(query).map { pagingData -> pagingData.map { it.toUIModel() } }

}