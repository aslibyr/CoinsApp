package com.app.coins.ui.nft

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _collectionAddress = MutableLiveData<String>()

    val collectionAddresses = mutableListOf<String>()


}
