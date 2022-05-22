package com.orlandev.testmobile.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.ui.screens.ProductViewModel
import com.orlandev.testmobile.ui.screens.home.ShimmerImage
import com.orlandev.testmobile.ui.theme.TestMobileTheme

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


@Composable
fun CartWidget(product: Product?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red),
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
            ) {
                Text(text = product?.name ?: "Name", style = MaterialTheme.typography.caption)
                Text(
                    text = product?.description
                        ?: "Description sf fkjs fkjs dfkjs fkjns fkj sf ehwb whrwejhr wehjrg wjherwe rwjehr jwher jewhr jwer jhwer ",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$ ${product?.price ?: "45.99"}",
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.End
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                shape = CircleShape,
                onClick = { /*TODO*/ }) {

                Icon(Icons.Default.ShoppingCartCheckout, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Comprar".uppercase())
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CartWidgetPreview() {
    TestMobileTheme {
        CartWidget(null)
    }
}