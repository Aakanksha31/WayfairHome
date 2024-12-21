package com.self.wayfairhome.repo

import com.self.wayfairhome.data.remote.ProductsRemoteSource
import com.self.wayfairhome.data.remote.ProductsRemoteSourceImpl
import com.self.wayfairhome.data.remote.ProductsService
import com.self.wayfairhome.data.repo.ProductsRepository
import com.self.wayfairhome.data.repo.ProductsRepositoryImpl
import com.self.wayfairhome.viewmodel.HomeProduct
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsRepositoryImplTest {
    @Mock
    lateinit var remoteSource: ProductsRemoteSource
    private lateinit var repository: ProductsRepository

    @Before
    fun setUp() {
        repository = ProductsRepositoryImpl(remoteSource)
    }

    @Test
    fun getProducts(): Unit = runBlocking {
        val list = listOf<HomeProduct>()
        Mockito.`when`(remoteSource.getProducts())
            .thenReturn(list)
        // call
        repository.getProducts()
        //verify
        Mockito.verify(remoteSource).getProducts()
    }
}