package com.samuelrmos.mobiledemo

import com.samuelrmos.mobiledemo.HttpClientMock.getHttpClientError
import com.samuelrmos.mobiledemo.HttpClientMock.getHttpClientSuccess
import com.samuelrmos.mobiledemo.kotlin.ProductApi
import com.samuelrmos.mobiledemo.kotlin.RequestState
import io.ktor.client.HttpClient
import io.mockk.MockKAnnotations.init
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductApiTest {

    private lateinit var productApi: ProductApi

    private fun setUp(httpClient: HttpClient) {
        init(this, relaxed = true)
        productApi = ProductApi(httpClient)
    }

    @Test
    fun fetchProducts_shouldEmitLoadingAndSuccess_whenHttpGetReturnsItems() {
        setUp(getHttpClientSuccess())
        runBlocking {
            val returned = productApi.fetchProducts(10)

            assertEquals(RequestState.Loading, returned.first())
            returned.last().getProductList()[0].run {
                assertEquals(1, id)
                assertEquals("T-shirt", title)
                assertEquals(100.0, price)
                assertEquals("Unisex", category)
                assertEquals("Unisex", description)
                assertEquals("/image", image)
            }
        }
    }

    @Test
    fun fetchProducts_shouldEmitLoadingAndError_whenHttpGetReturnsError() {
        setUp(getHttpClientError())
        runBlocking {
            val returned = productApi.fetchProducts(10)

            assertEquals(RequestState.Loading, returned.first())
            assertEquals("Error while fetching the data.", returned.last().getErrorMessage())
        }
    }
}