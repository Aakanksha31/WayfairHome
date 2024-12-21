package com.self.wayfairhome.data.repo

import com.self.wayfairhome.viewmodel.HomeProduct

interface ProductsRepository {
    suspend fun getProducts(): List<HomeProduct>
}