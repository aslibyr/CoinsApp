package com.app.coins.data.model

import com.squareup.moshi.Json

data class MarketResponse(

	@Json(name = "volume")
	val volume: Long? = null,

	@Json(name = "marketCap")
	val marketCap: Long? = null,

	@Json(name = "volumeChange")
	val volumeChange: Double? = null,

	@Json(name = "marketCapChange")
	val marketCapChange: Double? = null,

	@Json(name = "btcDominanceChange")
	val btcDominanceChange: Double? = null,

	@Json(name = "btcDominance")
	val btcDominance: Double? = null
)
