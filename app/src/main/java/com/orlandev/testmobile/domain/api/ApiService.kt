package com.orlandev.testmobile.domain.api

import com.orlandev.testmobile.domain.model.Product

interface ApiService {
    suspend fun getProductList(): List<Product>
}