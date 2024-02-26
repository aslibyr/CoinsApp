package com.app.coins.data.model

import com.squareup.moshi.Json

data class CollectionAssetsResponse(

	@Json(name = "data")
	val data: List<AssetsDataItem?>? = null,

	@Json(name = "meta")
	val meta: Meta? = null
)

data class AssetsDataItem(

    @Json(name = "standard")
	val standard: String? = null,

    @Json(name = "address")
	val address: String? = null,

    @Json(name = "previewUrl")
    val previewUrl: String? = null,

    @Json(name = "tokenId")
    val tokenId: String? = null,

    @Json(name = "listSource")
    val listSource: ListSource? = null,

    @Json(name = "rarityScore")
    val rarityScore: Double? = null,

    @Json(name = "source")
    val source: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "rarityRank")
    val rarityRank: Int? = null,

    @Json(name = "blockchain")
	val blockchain: String? = null,

    @Json(name = "name")
	val name: String? = null,

    @Json(name = "attributes")
	val attributes: List<AttributesItem?>? = null,

    @Json(name = "collectionId")
	val collectionId: String? = null,

    @Json(name = "listPrice")
    val listPrice: Double? = null
)

data class AttributesItem(

    @Json(name = "onSaleCount")
    val onSaleCount: Int? = null,

    @Json(name = "floorAskPrice")
    val floorAskPrice: Double? = null,

    @Json(name = "createdAt")
    val createdAt: String? = null,

    @Json(name = "kind")
    val kind: String? = null,

    @Json(name = "tokenCount")
    val tokenCount: Int? = null,

    @Json(name = "topBidValue")
    val topBidValue: Double? = null,

    @Json(name = "value")
    val value: String? = null,

    @Json(name = "key")
    val key: String? = null
)

data class ListSource(

	@Json(name = "domain")
	val domain: String? = null,

	@Json(name = "name")
	val name: String? = null,

	@Json(name = "icon")
	val icon: String? = null,

	@Json(name = "id")
	val id: String? = null,

	@Json(name = "url")
	val url: String? = null
)


