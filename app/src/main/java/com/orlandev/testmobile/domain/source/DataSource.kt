package com.orlandev.testmobile.domain.source

import com.orlandev.testmobile.domain.model.Product

interface DataSource {
    suspend fun getProductList(): List<Product>
}