package com.example.checkdots

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CheckDotsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = {
            androidLogger()
            androidContext(this@CheckDotsApp)
        })
    }
}