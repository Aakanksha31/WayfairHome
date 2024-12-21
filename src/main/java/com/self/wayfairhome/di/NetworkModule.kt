package com.self.wayfairhome.di

import com.self.wayfairhome.data.remote.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun getTestNetwork(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

}