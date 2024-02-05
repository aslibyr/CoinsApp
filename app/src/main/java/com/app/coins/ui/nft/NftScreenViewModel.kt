package com.app.coins.ui.nft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.data.model.NftResponse
import com.app.coins.data.remote.webservice.WebService
import com.app.coins.utils.ResultWrapper
import com.app.coins.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NftScreenViewModel @Inject constructor(private val webService: WebService) : ViewModel() {

    private val _uiState = MutableStateFlow(NftUIModel(nftData = null))
    val uiState = _uiState.asStateFlow()

    init {
        getNfts()
    }

    fun getNfts() {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) { webService.getNfts() }) {
                is ResultWrapper.GenericError -> {
                    _uiState.update {
                        it.copy(errorMessage = response.error.toString())
                    }
                }

                ResultWrapper.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                ResultWrapper.NetworkError -> {
                    _uiState.update {
                        it.copy(errorMessage = "İnternet bağlantınızı kontrol edin.")
                    }
                }

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            nftData = response.value,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class NftUIModel(
    val nftData: NftResponse?,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)