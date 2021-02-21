package com.sliide.philip.arnold

import android.app.Application
import com.sliide.philip.arnold.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class MainApplication: Application() , KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}