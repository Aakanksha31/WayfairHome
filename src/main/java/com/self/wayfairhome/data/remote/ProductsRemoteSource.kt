package com.self.wayfairhome.data.remote

import com.self.wayfairhome.viewmodel.HomeProduct

interface ProductsRemoteSource {
    suspend fun getProducts(): List<HomeProduct>
}