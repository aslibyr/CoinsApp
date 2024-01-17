package com.app.coins.data.remote.dataSource

import com.app.coins.data.remote.webservice.WebService
import javax.inject.Inject

class ExampleDataSource @Inject constructor(
    private val webService: WebService
) {
}