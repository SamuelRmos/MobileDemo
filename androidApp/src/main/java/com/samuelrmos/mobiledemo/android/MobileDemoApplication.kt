package com.samuelrmos.mobiledemo.android

import android.app.Application
import com.samuelrmos.mobiledemo.android.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MobileDemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MobileDemoApplication)
            modules(provideDependency())
        }
    }

    private fun provideDependency() = appComponent
}