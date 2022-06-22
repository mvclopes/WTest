package com.mvclopes.wtest

import android.app.Application
import com.mvclopes.wtest.di.postalCodeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(postalCodeModule)
        }
    }
}