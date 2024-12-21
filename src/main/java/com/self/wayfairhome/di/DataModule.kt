package com.self.wayfairhome.di

import com.self.wayfairhome.data.remote.ProductsRemoteSource
import com.self.wayfairhome.data.remote.ProductsRemoteSourceImpl
import com.self.wayfairhome.data.repo.ProductsRepository
import com.self.wayfairhome.data.repo.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    abstract fun productsRepository(item: ProductsRepositoryImpl): ProductsRepository

    @Binds
    abstract fun productsRemoteSource(item: ProductsRemoteSourceImpl): ProductsRemoteSource

}