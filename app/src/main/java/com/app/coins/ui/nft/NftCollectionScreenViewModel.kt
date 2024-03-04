package com.app.coins.ui.nft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.coins.data.model.DataItem
import com.app.coins.data.model.NftCollectionResponse
import com.app.coins.data.paging.NftPagingSource
import com.app.coins.data.remote.webservice.WebService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NftCollectionScreenViewModel @Inject constructor(
    private val webService: WebService,

    ) :
    ViewModel() {

    private val _uiState = MutableStateFlow(NftUIStateModel())
    val uiState = _uiState.asStateFlow()
    val nfts: Flow<PagingData<DataItem>> = Pager(PagingConfig(pageSize = 20)) {
        NftPagingSource(webService)
    }.flow.cachedIn(viewModelScope)

    fun changedLoadingState(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }
}


data class NftUIStateModel(
    val nftData: NftCollectionResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
)