package com.self.wayfairhome.viewmodel

data class HomeProductsUiState(
    val isLoading: Boolean,
    val items: List<HomeProduct>
) {
    companion object {
        fun emptyModel() =
            HomeProductsUiState(isLoading = false, items = emptyList())
    }
}

data class HomeProduct(
    val name: String,
    val tagline: String,
    val date: String,
    val rating: Double
)
