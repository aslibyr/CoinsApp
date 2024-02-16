package com.app.coins.ui.nft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.data.model.NftCollectionResponse
import com.app.coins.data.remote.webservice.WebService
import com.app.coins.utils.ResultWrapper
import com.app.coins.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NftCollectionScreenViewModel @Inject constructor(
    private val webService: WebService,

    ) :
    ViewModel() {

    private val _uiState = MutableStateFlow(NftUIStateModel(nftData = null))
    val uiState = _uiState.asStateFlow()

    val collectionAddresses = mutableListOf<String>()


    init {
        getNftCollections()
    }


    private fun getNftCollections() {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) { webService.getNftCollections() }) {
                is ResultWrapper.Success -> {
                    val addresses = response.value.data?.mapNotNull { it?.address } ?: emptyList()
                    setCollectionAddresses(addresses)
                }
                // Handle other result types
                else -> {
                    error("error")
                }
            }
        }
    }

    private fun setCollectionAddresses(addresses: List<String>) {
        collectionAddresses.clear()
        collectionAddresses.addAll(addresses)
    }
}


data class NftUIStateModel(
    val nftData: NftCollectionResponse?,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)