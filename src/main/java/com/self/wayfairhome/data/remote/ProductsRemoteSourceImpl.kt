package com.self.wayfairhome.data.remote

import com.self.wayfairhome.viewmodel.HomeProduct
import javax.inject.Inject

class ProductsRemoteSourceImpl @Inject constructor(private val productsService: ProductsService) :
    ProductsRemoteSource {
    override suspend fun getProducts(): List<HomeProduct> = productsService.getProducts()
}