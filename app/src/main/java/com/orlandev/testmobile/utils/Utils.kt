package com.orlandev.testmobile.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.orlandev.testmobile.domain.model.Product
import java.util.*
import kotlin.random.Random

fun generateFakeProducts(): List<Product> {

    val list = mutableListOf<Product>()
    for (i in 1..MAX_PRODUCTS_BY_PAGE) {
        list.add(
            Product(
                id = UUID.randomUUID().toString(),
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

@Composable
fun ForegroundGradientEffect(backgroundColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.60f, Color.Transparent),
                        Pair(1.8f, backgroundColor.copy(alpha = 0.8f))
                    )
                )
            )
    )
}