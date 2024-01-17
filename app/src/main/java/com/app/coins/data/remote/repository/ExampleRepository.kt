package com.app.coins.data.remote.repository

import com.app.coins.data.remote.dataSource.ExampleDataSource
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) {
}