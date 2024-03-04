package com.app.coins.ui.nft.collectiondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.coins.data.model.AssetsDataItem
import com.app.coins.data.model.CollectionAssetsResponse
import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.data.paging.AssetsPagingSource
import com.app.coins.data.remote.webservice.WebService
import com.app.coins.utils.ResultWrapper
import com.app.coins.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val address = checkNotNull(savedStateHandle.get<String>("collectionAddress"))
    private val _uiState = MutableStateFlow(CollectionUIStateModel())
    val uiState = _uiState.asStateFlow()
    val assets: Flow<PagingData<AssetsDataItem>> = Pager(PagingConfig(pageSize = 20)) {
        AssetsPagingSource(webService, collectionAddress = address)
    }.flow.cachedIn(viewModelScope)

    init {
        fetchCollectionDetails(collectionAddress = address)
    }

    private fun fetchCollectionDetails(collectionAddress: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                safeApiCall(Dispatchers.IO) { webService.getNftCollectionDetails(collectionAddress) }) {
                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            collectionData = response.value,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is ResultWrapper.GenericError -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = response.error.toString(),
                            isLoading = false
                        )
                    }
                }

                is ResultWrapper.NetworkError -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = "İnternet bağlantınızı kontrol edin.",
                            isLoading = false
                        )
                    }
                }

                ResultWrapper.Loading -> {

                }
            }
        }
    }

}

data class CollectionUIStateModel(
    val collectionData: CollectionDetailResponse? = null,
    val assetsData: CollectionAssetsResponse? = null,
    val isAssetsSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)