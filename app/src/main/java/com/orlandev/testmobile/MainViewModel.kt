package com.orlandev.testmobile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlandev.testmobile.data.source.remote.FakeRemoteDataSource
import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product
import com.orlandev.testmobile.domain.providers.ILocationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     private val apiService: ApiService,
     val userlocation: ILocationProvider
) : ViewModel() {

     val products: Flow<PagingData<Product>> = Pager(PagingConfig(pageSize = 6)) {
          FakeRemoteDataSource(apiService = apiService)
     }.flow.cachedIn(viewModelScope)

     //Esto es solo una simulaciòn... en una app real enviariamos una peticion al api con el ID del producto o una busqueda local en base de datos usada como cache de productos.
     // Cuando se seleccione un producto de la lista se guardara en esta variable y luego esta informacion se utilizara en la pantalla de detalles.
     private val _productSelected = mutableStateOf<Product?>(null)
     val productSelected = _productSelected

     fun onSelectProduct(product: Product) {
          productSelected.value = product
     }

     //Buy product simulation
     private val _userBuyProduct = mutableStateOf<Boolean>(false)
     val userBuyProduct = _userBuyProduct

     private val _showRequest = mutableStateOf<Boolean>(false)
     val showRequest = _showRequest

     private val _userAskAddToCart = mutableStateOf<Boolean>(false)
     val userAskAddToCart = _userAskAddToCart

     fun onAskUserBuy() {
          _userAskAddToCart.value = true
     }

     fun onClearAskUserBuy() {
          _userAskAddToCart.value = false
     }


     fun onUserBuyProduct() {
          viewModelScope.launch {
               _showRequest.value = true
               delay(2000) //Espera dos segundos para simular una peticion a un API en  internet
               _showRequest.value = false
               _userBuyProduct.value = true
          }
     }

     fun clearUserBuy() {
          _userBuyProduct.value = false
          _showRequest.value = false
     }

}
