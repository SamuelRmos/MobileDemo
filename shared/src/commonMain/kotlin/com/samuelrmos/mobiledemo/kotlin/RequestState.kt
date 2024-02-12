package com.samuelrmos.mobiledemo.kotlin

import kotlinx.serialization.Serializable

@Serializable
sealed class RequestState {
    data object Idle : RequestState()
    data object Loading : RequestState()

    data class Success(val data: Products) : RequestState()

    data class Error(val message: String): RequestState()

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getProducts(): Products = Products(items = (this as Success).data.items)
    fun getErrorMessage() = (this as Error).message
}
