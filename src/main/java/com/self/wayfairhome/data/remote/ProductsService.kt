package com.self.wayfairhome.data.remote

import com.self.wayfairhome.viewmodel.HomeProduct
import retrofit2.http.GET

interface ProductsService {
    @GET("json-to-list/products.v1.json")
    suspend fun getProducts(): List<HomeProduct>
}