package com.orlandev.testmobile.data.repositories

import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.domain.repository.DataRepository
import com.orlandev.testmobile.domain.source.DataSource

class RemoteRepository (private val dataSource: DataSource) : DataRepository {
    override suspend fun getProducts(): List<Product> {
        return dataSource.getProductList()
    }
}