package com.orlandev.testmobile.domain.repository

import com.orlandev.testmobile.domain.model.Product

interface DataRepository{
    suspend fun getProducts():List<Product>
}