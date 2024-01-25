package com.app.coins.domain.mapper

import com.app.coins.data.model.CryptoResponse
import com.app.coins.domain.model.CryptoUIModel

fun CryptoResponse.toUIModel() : CryptoUIModel {
    return CryptoUIModel(
        symbol = symbol,
        marketCap = marketCap?.toString() ?: "",
        priceChange1w = priceChange1w?.toString() ?: "",
        availableSupply = availableSupply?.toString() ?: "",
        totalSupply = totalSupply?.toString() ?: "",
        twitterUrl = twitterUrl ?: "",
        icon = icon ?: "",
        priceChange1h = priceChange1h?.toString() ?: "",
        priceBtc = priceBtc?.toString() ?: "",
        volume = volume?.toString() ?: "",
        redditUrl = redditUrl ?: "",
        priceChange1d = priceChange1d?.toString() ?: "",
        websiteUrl = websiteUrl ?: "",
        price = price?.toString() ?: "",
        explorers = explorers ?: emptyList(),
        name = name ?: "",
        rank = rank?.toString() ?: "",
        id = id ?: ""
    )
}