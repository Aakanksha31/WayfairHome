package com.self.wayfairhome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.self.wayfairhome.data.repo.ProductsRepository
import com.self.wayfairhome.model.OneOffEvent
import com.self.wayfairhome.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeProductsViewModel @Inject constructor(private val productsRepository: ProductsRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HomeProductsUiState.emptyModel())
    val uiState: StateFlow<HomeProductsUiState> = _uiState.asStateFlow()

    private val _oneOffEvent = Channel<OneOffEvent>()
    val oneOffEvent = _oneOffEvent.receiveAsFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.safeLaunch({
            //Pass actual List
            val cities = productsRepository.getProducts()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    items = cities
                )
            }
        }) { _, message ->
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
            viewModelScope.launch {
                _oneOffEvent.send(OneOffEvent.ShowError(message))
            }
        }
    }
}