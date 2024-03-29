package com.app.coins.ui.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.coins.data.model.CryptoResponse
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoScreenViewModel @Inject constructor(private val useCase: GetCoinsUseCase) :
    ViewModel() {

    private val _coinsState: MutableStateFlow<PagingData<CryptoUIModel>> =
        MutableStateFlow(value = PagingData.empty())
    val coinsState: MutableStateFlow<PagingData<CryptoUIModel>> get() = _coinsState

    init {
        getCoins()
    }

    private fun getCoins(query: String = "") {
        viewModelScope.launch {
            useCase.invoke(query).distinctUntilChanged().cachedIn(viewModelScope).collect {
                _coinsState.emit(it)
            }
        }
    }
}


