package com.samuelrmos.mobiledemo.kotlin

import co.touchlab.kermit.Logger
import com.samuelrmos.mobiledemo.kotlin.Constants.baseUrl
import com.samuelrmos.mobiledemo.kotlin.RequestState.Error
import com.samuelrmos.mobiledemo.kotlin.RequestState.Loading
import com.samuelrmos.mobiledemo.kotlin.RequestState.Success
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class ProductApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    fun fetchProducts(limit: Int): Flow<RequestState> = flow {
        emit(Loading)
        delay(2000)
        try {
            emit(
                Success(
                    data = Products(
                        items = httpClient.get("${baseUrl}products?limit$limit").body()
                    )
                )
            )
        } catch (exception: Exception) {
            Logger.run {
                setTag("ProductApi")
                e(exception.message.toString(), exception)
            }
            emit(Error("Error while fetching the data."))
        }
    }
}