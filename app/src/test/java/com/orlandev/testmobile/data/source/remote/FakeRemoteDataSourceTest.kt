package com.orlandev.testmobile.data.source.remote

import com.orlandev.testmobile.data.api.FakeApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.system.measureTimeMillis


class FakeRemoteDataSourceTest {

    @Test
    fun getProductList() = runBlocking {

        val apiServiceTest = FakeApiService()
        val fakeDataSource = FakeRemoteDataSource(apiService = apiServiceTest)

        val totalExecutionTime = measureTimeMillis {
            val products = fakeDataSource.getProductList()
            //Verify if products list is not empty
            Assert.assertTrue(products.isNotEmpty())
        }

        print("Total Execution Time: $totalExecutionTime\n")
    }
}