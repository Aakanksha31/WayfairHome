package com.self.wayfairhome.data.repo

import com.self.wayfairhome.viewmodel.HomeProduct
import com.self.wayfairhome.data.remote.ProductsRemoteSource
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsRemoteSource: ProductsRemoteSource) :
    ProductsRepository {
    override suspend fun getProducts(): List<HomeProduct> = productsRemoteSource.getProducts()
}