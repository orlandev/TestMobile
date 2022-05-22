package com.orlandev.testmobile.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.ui.screens.ProductViewModel
import com.orlandev.testmobile.ui.screens.home.ShimmerImage
import com.orlandev.testmobile.ui.theme.TestMobileTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val currentProduct = productViewModel.productSelected

    //ACOMPANIST PERRMISIONS
    // Track if the user doesn't want to see the rationale any more.
    var doNotShowRationale = rememberSaveable { mutableStateOf(false) }

    // FUSE Location permission state
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
        ) {
            //MAP
        }
        CartWidget(
            modifier = Modifier.align(Alignment.BottomCenter),
            product = currentProduct.value
        )

    }
}


@Composable
fun CartWidget(modifier: Modifier = Modifier, product: Product?) {
    Card(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerImage(
                    imgUrl = product?.thumbnailUrl ?: "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
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
            Divider()
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
}


@Preview(showBackground = true)
@Composable
fun CartWidgetPreview() {
    TestMobileTheme {
        CartWidget(product = null)
    }
}