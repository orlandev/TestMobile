package com.orlandev.testmobile.data.remote

import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.domain.source.DataSource
import javax.inject.Inject

class FakeRemoteDataSource @Inject constructor(private val apiService: ApiService) : DataSource {
    override suspend fun getProductList(): List<Product> {
        return apiService.getProductList()
    }
}