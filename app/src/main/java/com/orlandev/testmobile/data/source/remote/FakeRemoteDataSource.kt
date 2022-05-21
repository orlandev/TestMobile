package com.orlandev.testmobile.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.model.Product

class FakeRemoteDataSource(private val apiService: ApiService) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        val nextPage = params.key ?: 1
        val userList = apiService.getProductList()
        return LoadResult.Page(
            data = userList,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = if (userList.isEmpty()) null else nextPage + 1
        )
    }
}


