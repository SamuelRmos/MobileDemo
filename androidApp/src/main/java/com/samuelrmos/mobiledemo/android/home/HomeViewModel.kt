package com.samuelrmos.mobiledemo.android.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelrmos.mobiledemo.kotlin.ProductApi
import com.samuelrmos.mobiledemo.kotlin.RequestState
import com.samuelrmos.mobiledemo.kotlin.RequestState.Idle
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val productApi: ProductApi): ViewModel() {

    private var _requestState: MutableState<RequestState> = mutableStateOf(Idle)
    val requestState = _requestState

    init {
        viewModelScope.launch(Main) {
            productApi.fetchProducts(10).collectLatest {
                _requestState.value = it
            }
        }
    }
}