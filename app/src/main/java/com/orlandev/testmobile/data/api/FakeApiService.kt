package com.orlandev.testmobile.data.api

import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.utils.generateFakeProducts
import kotlinx.coroutines.delay

class FakeApiService : ApiService {
    override suspend fun getProductList(): List<Product> {
        //Simula la espera al hacer una peticiòn a un API
        delay(3000)
        return generateFakeProducts()
    }
}


