package com.orlandev.testmobile.di

import com.orlandev.testmobile.data.api.FakeApiService
import com.orlandev.testmobile.data.providers.location.FakeLocationProvider
import com.orlandev.testmobile.data.source.remote.FakeRemoteDataSource
import com.orlandev.testmobile.domain.api.ApiService
import com.orlandev.testmobile.domain.providers.ILocationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return FakeApiService()
    }

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): FakeRemoteDataSource {
        return FakeRemoteDataSource(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideUserLocation(): ILocationProvider {
        return FakeLocationProvider()
    }


}