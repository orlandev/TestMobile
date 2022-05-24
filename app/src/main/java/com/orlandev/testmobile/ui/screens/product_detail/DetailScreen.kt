package com.orlandev.testmobile.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.orlandev.testmobile.MainViewModel
import com.orlandev.testmobile.R
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.navigation.NavigationRoute
import com.orlandev.testmobile.ui.theme.TestMobileTheme
import com.orlandev.testmobile.utils.ShimmerImage

@Composable
fun DetailScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val currentProduct = mainViewModel.productSelected

    val fakeUserLocation = mainViewModel.userlocation.getUserLocation()


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Column {

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(fakeUserLocation, 13f)
                }
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = fakeUserLocation),
                        title = "Fake User Position"
                    )
                }
            }
        }
        CartWidget(
            modifier = Modifier.align(Alignment.BottomCenter),
            product = currentProduct.value
        ) {
            mainViewModel.onAskUserBuy()
        }

        // Mostramos un cuadro de dialogo para verificar la compra del prouducto
        if (mainViewModel.userAskAddToCart.value) {
            AlertDialog(
                onDismissRequest = {
                    mainViewModel.onClearAskUserBuy()
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                text = {
                    Text(text = stringResource(id = R.string.add_to_cart_text_alert_dialog))
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(3f),
                            onClick = { mainViewModel.onClearAskUserBuy() }
                        ) {
                            Text(stringResource(id = R.string.no_alert_dialg))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            modifier = Modifier.weight(3f),
                            onClick = {
                                mainViewModel.onUserBuyProduct()
                                mainViewModel.onClearAskUserBuy()
                            }
                        ) {
                            Text(stringResource(id = R.string.yes_alert_dialg))
                        }
                    }
                }
            )
        }

        if (mainViewModel.showRequest.value) {
            AlertDialog(
                onDismissRequest = {
                },
                text = {

                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.process_request))
                    }

                },
                buttons = { }
            )
        }

        if (mainViewModel.userBuyProduct.value) {
            AlertDialog(
                onDismissRequest = {
                    mainViewModel.clearUserBuy()
                },
                text = {
                    Text(text = stringResource(id = R.string.buy_sucessfull))
                },
                buttons = {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(
                            onClick = {
                                mainViewModel.clearUserBuy()
                                navController.navigate(NavigationRoute.HomeScreenRoute.route)
                            }
                        ) {
                            Text(stringResource(id = R.string.acept_alert_dialg).uppercase())
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun CartWidget(modifier: Modifier = Modifier, product: Product?, onClick: () -> Unit) {
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
                    onClick = onClick
                ) {

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
        CartWidget(product = null, onClick = {})
    }
}