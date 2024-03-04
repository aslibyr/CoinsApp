package com.app.coins.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.coins.data.model.ResultItem
import com.app.coins.data.remote.webservice.WebService
import okio.IOException
import retrofit2.HttpException

class NewsPagingSource(
    private val webService: WebService,
) : PagingSource<Int, ResultItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultItem> {
        return try {
            val currentPage = params.key ?: 1
            val news = webService.getNews(
                page = currentPage,
                limit = 20,
            )
            LoadResult.Page(
                data = news.result,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (news.result.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultItem>): Int? {
        return state.anchorPosition
    }
}