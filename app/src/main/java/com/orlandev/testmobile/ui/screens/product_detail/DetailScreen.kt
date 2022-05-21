package com.orlandev.testmobile.ui.screens.product_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlandev.testmobile.ui.screens.ProductViewModel
import com.orlandev.testmobile.ui.screens.home.ShimmerImage

@Composable
fun DetailScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val currentProduct = productViewModel.productSelected
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        currentProduct.value?.let {
            Column(modifier = Modifier) {
                ShimmerImage(
                    imgUrl = it.thumbnailUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Text(text = it.name)
            }
        } ?: Text(text = "NO VALUE")
    }
}