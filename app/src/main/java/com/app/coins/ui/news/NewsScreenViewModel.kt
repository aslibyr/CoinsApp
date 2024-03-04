package com.app.coins.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.coins.data.model.NewsResponse
import com.app.coins.data.model.ResultItem
import com.app.coins.data.paging.NewsPagingSource
import com.app.coins.data.remote.webservice.WebService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val webService: WebService
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUIStateModel())
    val uiState = _uiState.asStateFlow()
    val news: Flow<PagingData<ResultItem>> = Pager(PagingConfig(pageSize = 20)) {
        NewsPagingSource(webService)
    }.flow.cachedIn(viewModelScope)

    fun changedLoadingState(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

}

data class NewsUIStateModel(
    val newsData: NewsResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
)