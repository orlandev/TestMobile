package com.orlandev.testmobile.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.orlandev.testmobile.MainViewModel
import com.orlandev.testmobile.R
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.navigation.NavigationRoute
import com.orlandev.testmobile.utils.ShimmerImage
import com.orlandev.testmobile.utils.items

@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val productLazyList: LazyPagingItems<Product> =
        mainViewModel.products.collectAsLazyPagingItems()


    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(productLazyList) { currentProduct ->
            ProductItem(currentProduct) {
                mainViewModel.onSelectProduct(currentProduct!!)
                navController.navigate(NavigationRoute.DetailScreenRoute.route)
            }
        }
        productLazyList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.padding(8.dp)) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(
                                        Alignment.TopCenter
                                    )
                            )
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(id = R.string.loading_products)
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.padding(8.dp)) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(
                                        Alignment.BottomCenter
                                    )
                            )
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(id = R.string.loading_products)
                        )
                    }
                }
                loadState.append is LoadState.Error -> {

                    item {

                        Text(text = "ERROR...")

                    }

                }
            }
        }
    }
}

@Composable
fun ProductItem(currentProduct: Product?, onItemClick: () -> Unit) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            ShimmerImage(
                imgUrl = currentProduct!!.thumbnailUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = currentProduct.name,
                style = MaterialTheme.typography.caption
            )

        }
    }
}

