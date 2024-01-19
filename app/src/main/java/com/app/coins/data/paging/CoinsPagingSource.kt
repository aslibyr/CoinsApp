package com.app.coins.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.coins.data.model.CryptoResponse
import com.app.coins.data.remote.webservice.WebService
import okio.IOException
import retrofit2.HttpException

class CoinsPagingSource(
    private val webService: WebService,
    private val query: String = "",
    private val currency: String = "USD"
) : PagingSource<Int, CryptoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoResponse> {
        return try {
            val currentPage = params.key ?: 1
            val coins = webService.getCoins(
                page = currentPage,
                limit = 20,
                currency = currency,
                blockchain = query
            )
            LoadResult.Page(
                data = coins.result,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (coins.result.isEmpty()) null else coins.meta.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CryptoResponse>): Int? {
        return state.anchorPosition
    }
}