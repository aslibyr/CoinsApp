package com.app.coins.data.remote.webservice

import com.app.coins.data.model.BasePagingResponse
import com.app.coins.data.model.CryptoResponse
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
    suspend fun getCoinDetail(@Path("coinId") coinId : String) : CryptoResponse

}
