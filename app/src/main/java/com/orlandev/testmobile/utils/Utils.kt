package com.orlandev.testmobile.utils

import com.orlandev.testmobile.domain.model.Product
import kotlin.random.Random

fun generateFakeProducts(): List<Product> {

    val list = mutableListOf<Product>()
    for (i in 1..MAX_PRODUCTS_BY_PAGE) {
        list.add(
            Product(
                name = "ProductName",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                thumbnailUrl = "https://source.unsplash.com/random/${
                    Random.nextInt(
                        300,
                        400
                    )
                }×${Random.nextInt(200, 300)}"
            )
        )
    }

    return list

}