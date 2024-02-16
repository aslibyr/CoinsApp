package com.app.coins.data.remote.dataSource

import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.remote.webservice.WebService
import com.app.coins.utils.ResultWrapper
import com.app.coins.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NftRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getNftCollectionDetails(collectionAddress: String): ResultWrapper<CollectionDetailResponse> =
        safeApiCall(Dispatchers.IO) { webService.getNftCollectionDetails(collectionAddress) }
}