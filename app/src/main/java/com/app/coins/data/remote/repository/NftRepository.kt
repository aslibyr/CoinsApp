package com.app.coins.data.remote.repository

import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.remote.webservice.WebService
import javax.inject.Inject

class NftRepository @Inject constructor(private val webService: WebService) {
    suspend fun getNftCollectionDetailsForAll(response: CollectionDetailResponse): List<CollectionDetailResponse> {
        val detailResponses = mutableListOf<CollectionDetailResponse>()

        response.data?.forEach { item ->
            item?.address?.let { address ->
                val detailResponse = webService.getNftCollectionDetails(address)
                detailResponses.add(detailResponse)
            }
        }
        return detailResponses
    }
}
