package com.app.coins.data.remote.repository

import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.remote.dataSource.NftRemoteDataSource
import com.app.coins.utils.ResultWrapper
import javax.inject.Inject

class NftRepository @Inject constructor(private val nftRemoteDataSource: NftRemoteDataSource) {
    suspend fun getNftCollectionDetailsForAll(collectionAddress: String): ResultWrapper<CollectionDetailResponse> =
        nftRemoteDataSource.getNftCollectionDetails(collectionAddress)
}

