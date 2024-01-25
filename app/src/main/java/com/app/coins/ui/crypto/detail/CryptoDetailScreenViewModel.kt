package com.app.coins.ui.crypto.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.domain.BaseUIModel
import com.app.coins.domain.Loading
import com.app.coins.domain.model.CryptoUIModel
import com.app.coins.domain.use_case.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CryptoDetailScreenViewModel @Inject constructor(
    private val coinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val coinId = checkNotNull(savedStateHandle.get<String>("id"))
    private val _coinDetailState = MutableStateFlow<BaseUIModel<CryptoUIModel>>(Loading())
    val coinDetailState: StateFlow<BaseUIModel<CryptoUIModel>>
        get() = _coinDetailState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Loading()
        )
    init {
        getCoinDetail(coinId)
    }
    private fun getCoinDetail(coinId:String) {
        viewModelScope.launch(Dispatchers.IO) {
            coinDetailUseCase.invoke(coinId).collect { coinDetailState ->
                _coinDetailState.emit(coinDetailState)
            }
        }
    }
}