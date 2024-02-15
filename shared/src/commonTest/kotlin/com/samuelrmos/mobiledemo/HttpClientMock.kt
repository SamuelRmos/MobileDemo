package com.samuelrmos.mobiledemo

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel

object HttpClientMock {

    fun getHttpClientSuccess() = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json()
        }
        engine {
            addHandler {
                respond(
                    content = ByteReadChannel(
                        "     [\n" +
                                "                {\n" +
                                "                    id:1,\n" +
                                "                    title: T-shirt,\n" +
                                "                    price: 100.0,\n" +
                                "                    category: Unisex,\n" +
                                "                    description: Unisex,\n" +
                                "                    image: /image\n" +
                                "                }]"
                    ),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }
    }

    fun getHttpClientError() = HttpClient(MockEngine) {
        engine { addHandler { respondError(HttpStatusCode.BadRequest) } }
    }
}