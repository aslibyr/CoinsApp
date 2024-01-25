package com.app.coins.domain.model

data class CryptoUIModel(
    val symbol: String?,
    val marketCap: String?,
    val priceChange1w: String?,
    val availableSupply: String?,
    val totalSupply: String?,
    val twitterUrl: String?,
    val icon: String?,
    val priceChange1h: String?,
    val priceBtc: String?,
    val volume: String?,
    val redditUrl: String?,
    val priceChange1d: String?,
    val websiteUrl: String?,
    val price: String?,
    val explorers: List<String?>?,
    val name: String?,
    val rank: String?,
    val id: String?
)
