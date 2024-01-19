package com.app.coins.data.model

import com.squareup.moshi.Json

data class CryptoResponse(

	@Json(name = "symbol")
	val symbol: String? = null,

	@Json(name = "marketCap")
	val marketCap: Double? = null,

	@Json(name = "priceChange1w")
	val priceChange1w: Double? = null,

	@Json(name = "availableSupply")
	val availableSupply: Int? = null,

	@Json(name = "totalSupply")
	val totalSupply: Int? = null,

	@Json(name = "twitterUrl")
	val twitterUrl: String? = null,

	@Json(name = "icon")
	val icon: String? = null,

	@Json(name = "priceChange1h")
	val priceChange1h: Double? = null,

	@Json(name = "priceBtc")
	val priceBtc: Int? = null,

	@Json(name = "volume")
	val volume: Double? = null,

	@Json(name = "redditUrl")
	val redditUrl: String? = null,

	@Json(name = "priceChange1d")
	val priceChange1d: Double? = null,

	@Json(name = "websiteUrl")
	val websiteUrl: String? = null,

	@Json(name = "price")
	val price: Double? = null,

	@Json(name = "explorers")
	val explorers: List<String?>? = null,

	@Json(name = "name")
	val name: String? = null,

	@Json(name = "rank")
	val rank: Int? = null,

	@Json(name = "id")
	val id: String? = null
)
