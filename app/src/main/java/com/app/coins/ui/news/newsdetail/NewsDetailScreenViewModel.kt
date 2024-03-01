package com.app.coins.ui.news.newsdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.data.model.NewsDetailResponse
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
class NewsDetailScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _uiState = MutableStateFlow(NewsDetailUIStateModel())
    val uiState = _uiState.asStateFlow()

    init {
        getNewsDetails(id = id)
    }

    private fun getNewsDetails(id: String) {
        viewModelScope.launch {
            when (val response =
                safeApiCall(Dispatchers.IO) { webService.getNewsDetails(id) }) {
                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            newsDetailData = response.value,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
                // Handle other result types
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
            }
        }
    }
}

data class NewsDetailUIStateModel(
    val newsDetailData: NewsDetailResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val isSuccess: Boolean = false
)