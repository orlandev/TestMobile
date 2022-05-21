package com.orlandev.testmobile.utils

import com.orlandev.testmobile.domain.model.Product

fun generateFakeProducts(): List<Product> {

    val list = mutableListOf<Product>()
    for (i in 1..50) {
        list.add(
            Product(
                name = "ProductName",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                thumbnailUrl = "https://source.unsplash.com/random/300×300"
            )
        )
    }

    return list

}