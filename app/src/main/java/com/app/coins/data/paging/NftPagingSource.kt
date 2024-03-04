package com.app.coins.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.coins.data.model.DataItem
import com.app.coins.data.remote.webservice.WebService
import okio.IOException
import retrofit2.HttpException

class NftPagingSource(private val webService: WebService) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val currentPage = params.key ?: 1
            val nfts = webService.getNftCollections(
                page = currentPage,
                limit = 20,
            )
            LoadResult.Page(
                data = nfts.data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (nfts.data.isEmpty()) null else nfts.meta.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)

        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition
    }
}