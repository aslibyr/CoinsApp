package com.app.coins.ui.nft.collectiondetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coins.data.model.CollectionDetailResponse
import com.app.coins.domain.use_case.GetCollectionDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailScreenViewModel @Inject constructor(private val getCollectionDetailsUseCase: GetCollectionDetailsUseCase) :
    ViewModel() {
    private val _collectionDetails = MutableLiveData<List<CollectionDetailResponse>>()
    val collectionDetails: LiveData<List<CollectionDetailResponse>> get() = _collectionDetails

    fun fetchCollectionDetails(response: CollectionDetailResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val detailResponse = getCollectionDetailsUseCase.execute(response)
            _collectionDetails.value = detailResponse
        }

    }
}