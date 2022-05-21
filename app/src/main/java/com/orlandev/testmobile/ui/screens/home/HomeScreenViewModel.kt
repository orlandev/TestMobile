package com.orlandev.testmobile.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlandev.testmobile.data.source.remote.FakeRemoteDataSource
import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

     val products: Flow<PagingData<Product>> = Pager(PagingConfig(pageSize = 6)) {
          FakeRemoteDataSource(apiService = apiService)
     }.flow.cachedIn(viewModelScope)

}
