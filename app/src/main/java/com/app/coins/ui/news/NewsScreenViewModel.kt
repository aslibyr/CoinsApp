package com.app.coins.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.data.model.NewsResponse
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
class NewsScreenViewModel @Inject constructor(
    private val webService: WebService
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUIStateModel())
    val uiState = _uiState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) { webService.getNews() }) {
                is ResultWrapper.Success -> {
                    val newsResponse = response.value
                    _uiState.update {
                        it.copy(newsData = newsResponse, isLoading = false)
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

data class NewsUIStateModel(
    val newsData: NewsResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
)