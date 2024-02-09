package com.app.coins.domain.use_case

import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.remote.repository.NftRepository
import javax.inject.Inject

class GetCollectionDetailsUseCase @Inject constructor(private val repository: NftRepository) {
    suspend fun execute(response: CollectionDetailResponse): List<CollectionDetailResponse> {
        return repository.getNftCollectionDetailsForAll(response)
    }
}