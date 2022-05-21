package com.orlandev.testmobile.data.source.remote

import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.domain.source.DataSource

class FakeRemoteDataSource (private val apiService: ApiService) : DataSource {
    override suspend fun getProductList(): List<Product> {
        return apiService.getProductList()
    }
}