package com.orlandev.testmobile.data.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.system.measureTimeMillis


class FakeApiServiceTest {

    @Test
    fun getProductList() = runBlocking {

        val apiServiceTest = FakeApiService()

        val totalExecutionTime = measureTimeMillis {
            val products = apiServiceTest.getProductList()
            //Verify if products list is not empty
            Assert.assertTrue(products.isNotEmpty())
        }

        print("Total Execution Time: $totalExecutionTime\n")
    }

}