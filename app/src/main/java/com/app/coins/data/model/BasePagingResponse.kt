package com.app.coins.data.model

data class BasePagingResponse<T>(
    val result: List<T>,
    val meta: Meta
)

data class Meta(
    val page: Int,
    val limit: Int = 20,
    val itemCount: Int,
    val pageCount: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
