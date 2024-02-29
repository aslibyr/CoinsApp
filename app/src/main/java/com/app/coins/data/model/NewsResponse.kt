package com.app.coins.data.model

import com.squareup.moshi.Json

data class NewsResponse(

	@Json(name = "result")
	val result: List<ResultItem?>? = null,

	@Json(name = "total")
	val total: Int? = null
)

data class ResultItem(

	@Json(name = "feedDate")
	val feedDate: Long? = null,

	@Json(name = "reactionsCount")
	val reactionsCount: ReactionsCount? = null,

	@Json(name = "searchKeyWords")
	val searchKeyWords: List<String?>? = null,

	@Json(name = "sourceLink")
	val sourceLink: String? = null,

	@Json(name = "link")
	val link: String? = null,

	@Json(name = "source")
	val source: String? = null,

	@Json(name = "title")
	val title: String? = null,

	@Json(name = "content")
	val content: Boolean? = null,

	@Json(name = "imgUrl")
	val imgUrl: String? = null,

	@Json(name = "relatedCoins")
	val relatedCoins: List<String?>? = null,

	@Json(name = "bigImg")
	val bigImg: Boolean? = null,

	@Json(name = "reactions")
	val reactions: List<Any?>? = null,

	@Json(name = "shareURL")
	val shareURL: String? = null,

	@Json(name = "id")
	val id: String? = null,

	@Json(name = "isFeatured")
	val isFeatured: Boolean? = null
)

data class ReactionsCount(
	val any: Any? = null
)
