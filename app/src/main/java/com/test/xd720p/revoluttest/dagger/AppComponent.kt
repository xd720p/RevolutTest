package com.test.xd720p.revoluttest.dagger


import com.test.xd720p.revoluttest.CurrencyApplication
import com.test.xd720p.revoluttest.ui.CurrencyActivity
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(currencyApplication: CurrencyApplication)

    fun inject(currencyActivity: CurrencyActivity)
}
