package com.app.coins.data.model

import com.squareup.moshi.Json

data class CollectionDetailResponse(

	@Json(name = "data")
	val data: List<DataItem?>? = null,

	@Json(name = "meta")
	val meta: Meta? = null
)

data class RelevantUrlsItem2(

	@Json(name = "name")
	val name: String? = null,

	@Json(name = "url")
	val url: String? = null
)

data class Meta3(

	@Json(name = "pageCount")
	val pageCount: Int? = null,

	@Json(name = "hasNextPage")
	val hasNextPage: Boolean? = null,

	@Json(name = "limit")
	val limit: Int? = null,

	@Json(name = "hasPreviousPage")
	val hasPreviousPage: Boolean? = null,

	@Json(name = "page")
	val page: Int? = null,

	@Json(name = "itemCount")
	val itemCount: Int? = null
)

data class DataItem2(

	@Json(name = "volumeMc24h")
	val volumeMc24h: Double? = null,

	@Json(name = "img")
	val img: String? = null,

	@Json(name = "volumeUsd24h")
	val volumeUsd24h: Double? = null,

	@Json(name = "show")
	val show: Boolean? = null,

	@Json(name = "description")
	val description: String? = null,

	@Json(name = "source")
	val source: String? = null,

	@Json(name = "creatorFee")
	val creatorFee: Int? = null,

	@Json(name = "volumeUsd7d")
	val volumeUsd7d: Double? = null,

	@Json(name = "floorPriceChange7d")
	val floorPriceChange7d: Double? = null,

	@Json(name = "rank")
	val rank: Int? = null,

	@Json(name = "rankAll")
	val rankAll: Int? = null,

	@Json(name = "bannerImg")
	val bannerImg: String? = null,

	@Json(name = "slug")
	val slug: String? = null,

	@Json(name = "slugCs")
	val slugCs: String? = null,

	@Json(name = "address")
	val address: String? = null,

	@Json(name = "totalSupply")
	val totalSupply: Int? = null,

	@Json(name = "verified")
	val verified: Boolean? = null,

	@Json(name = "floorPriceMc")
	val floorPriceMc: Double? = null,

	@Json(name = "listedCount")
	val listedCount: Int? = null,

	@Json(name = "volumeMc7d")
	val volumeMc7d: Double? = null,

	@Json(name = "volume")
	val volume: Double? = null,

	@Json(name = "floorPriceChange24h")
	val floorPriceChange24h: Double? = null,

	@Json(name = "volumeChange24h")
	val volumeChange24h: Double? = null,

	@Json(name = "createdDate")
	val createdDate: String? = null,

	@Json(name = "blockchain")
	val blockchain: String? = null,

	@Json(name = "volumeChange7d")
	val volumeChange7d: Double? = null,

	@Json(name = "name")
	val name: String? = null,

	@Json(name = "relevantUrls")
	val relevantUrls: List<RelevantUrlsItem?>? = null,

	@Json(name = "ownersCount")
	val ownersCount: Int? = null,

	@Json(name = "floorPriceUsd")
	val floorPriceUsd: Double? = null,

	@Json(name = "totalVolume")
	val totalVolume: Double? = null,

	@Json(name = "salesInProfitChange7d")
	val salesInProfitChange7d: Int? = null,

	@Json(name = "thirtyDaySales")
	val thirtyDaySales: Int? = null,

	@Json(name = "salesInProfit")
	val salesInProfit: Int? = null,

	@Json(name = "thirtyDayVolume")
	val thirtyDayVolume: Double? = null,

	@Json(name = "ownersCountChange7d")
	val ownersCountChange7d: Double? = null,

	@Json(name = "salesInProfitChange24h")
	val salesInProfitChange24h: Int? = null,

	@Json(name = "count")
	val count: Int? = null,

	@Json(name = "oneDaySales")
	val oneDaySales: Int? = null,

	@Json(name = "marketcapUsd")
	val marketcapUsd: Double? = null,

	@Json(name = "transactionsUpdateDate")
	val transactionsUpdateDate: String? = null,

	@Json(name = "averagePrice")
	val averagePrice: Double? = null,

	@Json(name = "mainCurrencyId")
	val mainCurrencyId: String? = null,

	@Json(name = "oneDayAveragePrice")
	val oneDayAveragePrice: Double? = null,

	@Json(name = "marketcapChange7d")
	val marketcapChange7d: Double? = null,

	@Json(name = "sevenDayChange")
	val sevenDayChange: Double? = null,

	@Json(name = "thirtyDayAveragePrice")
	val thirtyDayAveragePrice: Double? = null,

	@Json(name = "sevenDayAveragePrice")
	val sevenDayAveragePrice: Double? = null,

	@Json(name = "totalSales")
	val totalSales: Int? = null,

	@Json(name = "thirtyDayChange")
	val thirtyDayChange: Double? = null,

	@Json(name = "marketcapChange24h")
	val marketcapChange24h: Double? = null,

	@Json(name = "oneDayChange")
	val oneDayChange: Double? = null,

	@Json(name = "ownersCountChange24h")
	val ownersCountChange24h: Int? = null,

	@Json(name = "sevenDaySales")
	val sevenDaySales: Int? = null,

	@Json(name = "marketcapMc")
	val marketcapMc: Double? = null
)
