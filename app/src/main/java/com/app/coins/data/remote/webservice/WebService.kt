package com.app.coins.data.remote.webservice

import com.app.coins.data.model.AssetsDataItem
import com.app.coins.data.model.BasePagingResponse
import com.app.coins.data.model.ChartsResponse
import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.model.DataItem
import com.app.coins.data.model.NewsDetailResponse
import com.app.coins.data.model.NftPagingResponse
import com.app.coins.data.model.ResultItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {
    @GET("coins")
    suspend fun getCoins(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("currency") currency: String,
        @Query("blockchain") blockchain: String,
    ): BasePagingResponse<CryptoResponse>

    @GET("coins/{coinId}")
    suspend fun getCoinDetail(@Path("coinId") coinId: String): CryptoResponse


    @GET("coins/{coinId}/charts")
    suspend fun getCoinCharts(
        @Path("coinId") coinId: String,
        @Query("period") period: String = "24h"
    ): List<ChartsResponse>

    @GET("nft/trending")
    suspend fun getNftCollections(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): NftPagingResponse<DataItem>

    @GET("nft/collection/{collectionAddress}")
    suspend fun getNftCollectionDetails(
        @Path("collectionAddress") collectionAddress: String
    ): CollectionDetailResponse

    @GET("nft/{collectionAddress}/assets")
    suspend fun getNftCollectionAssets(
        @Path("collectionAddress") collectionAddress: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): NftPagingResponse<AssetsDataItem>

    @GET("news")
    suspend fun getNews(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): BasePagingResponse<ResultItem>

    @GET("news/{id}")
    suspend fun getNewsDetails(
        @Path("id") id: String
    ): NewsDetailResponse
}
