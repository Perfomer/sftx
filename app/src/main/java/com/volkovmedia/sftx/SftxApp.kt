package com.volkovmedia.sftx

import android.app.Application
import org.koin.android.ext.android.startKoin

class SftxApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = koinModules
        )
    }

}