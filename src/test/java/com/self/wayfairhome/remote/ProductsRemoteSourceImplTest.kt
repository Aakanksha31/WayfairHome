package com.self.wayfairhome.remote

import com.self.wayfairhome.data.remote.ProductsRemoteSource
import com.self.wayfairhome.data.remote.ProductsRemoteSourceImpl
import com.self.wayfairhome.data.remote.ProductsService
import com.self.wayfairhome.data.repo.ProductsRepository
import com.self.wayfairhome.viewmodel.HomeProduct
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsRemoteSourceImplTest {
    @Mock
    lateinit var service: ProductsService
    private lateinit var remoteSource: ProductsRemoteSource

    @Before
    fun setUp() {
        remoteSource = ProductsRemoteSourceImpl(service)
    }

    @Test
    fun getProducts(): Unit = runBlocking {
        val list = listOf<HomeProduct>()
        Mockito.`when`(service.getProducts())
            .thenReturn(list)
        // call
        remoteSource.getProducts()
        //verify
        Mockito.verify(service).getProducts()
    }
}