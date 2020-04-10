package com.someapp.framework

import android.app.Application
import com.someapp.BuildConfig
import com.someapp.framework.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@AppClass)
            modules(listOf(appModule))
        }
    }
}