package com.orlandev.testmobile.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.orlandev.testmobile.domain.model.Product

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val productLazyList: LazyPagingItems<Product> =
        homeScreenViewModel.products.collectAsLazyPagingItems()


    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(productLazyList) { currentProduct ->
            Text(text = currentProduct!!.name)
        }
        productLazyList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {

                        Text(text = "REFRESHING...")

                    }

                }
                loadState.append is LoadState.Loading -> {

                    item {

                        Text(text = "Loading...")

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



/*

    val pager = remember {
        Pager(
            PagingConfig(
                pageSize = myBackend.DataBatchSize,
                enablePlaceholders = true,
                maxSize = 200
            )
        ) { homeScreenViewModel.products }
    }

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

    LazyColumn {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        itemsIndexed(lazyPagingItems) { index, item ->
            Text("Index=$index: $item", fontSize = 20.sp)
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
*/

}