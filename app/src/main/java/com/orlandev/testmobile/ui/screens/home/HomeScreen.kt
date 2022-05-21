package com.orlandev.testmobile.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.orlandev.testmobile.R
import com.orlandev.testmobile.domain.model.Product

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val productLazyList: LazyPagingItems<Product> =
        homeScreenViewModel.products.collectAsLazyPagingItems()


    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(productLazyList) { currentProduct ->

            Card(
                elevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp).clickable {

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

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = currentProduct!!.name, style = MaterialTheme.typography.caption)

                }
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

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) {
    items(count = items.itemCount) { index ->
        itemContent(items[index])
    }
}


@Composable
fun ShimmerImage(
    imgUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    colorShimmer: Color = Color.Gray
) {
    val painter =
        rememberImagePainter(
            data = imgUrl,
            builder = {
                crossfade(true)
            })

    val state = painter.state is AsyncImagePainter.State.Loading
    Image(
        modifier = modifier
            .placeholder(
                color = colorShimmer,
                visible = state,
                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
            ),
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
    )
}