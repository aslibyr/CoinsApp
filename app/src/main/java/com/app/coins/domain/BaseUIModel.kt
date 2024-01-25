package com.app.coins.domain

sealed class BaseUIModel<T>

class Loading<T> : BaseUIModel<T>()

data class Success<T>(val response : T) : BaseUIModel<T>()

data class GenericError<T>(val errorMessage : String) : BaseUIModel<T>()

class Empty<T> : BaseUIModel<T>()
