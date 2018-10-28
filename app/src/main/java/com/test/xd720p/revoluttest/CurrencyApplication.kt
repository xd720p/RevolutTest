package com.test.xd720p.revoluttest

import android.app.Application
import com.test.xd720p.revoluttest.dagger.AppComponent
import com.test.xd720p.revoluttest.dagger.DaggerAppComponent

class CurrencyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}