package com.samuelrmos.mobiledemo.android.di

import com.samuelrmos.mobiledemo.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { HomeViewModel(get()) }
}