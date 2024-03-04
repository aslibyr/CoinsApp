package com.app.coins.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.coins.data.model.AssetsDataItem
import com.app.coins.data.remote.webservice.WebService
import retrofit2.HttpException
import java.io.IOException

class AssetsPagingSource(
    private val webService: WebService,
    private val collectionAddress: String
) : PagingSource<Int, AssetsDataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AssetsDataItem> {
        return try {
            val currentPage = params.key ?: 1
            val assets = webService.getNftCollectionAssets(
                collectionAddress = collectionAddress,
                page = currentPage,
                limit = 20
            )
            LoadResult.Page(
                data = assets.data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (assets.data.isEmpty()) null else assets.meta.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)

        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AssetsDataItem>): Int? {
        return state.anchorPosition
    }
}