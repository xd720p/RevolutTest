package com.test.xd720p.revoluttest.ui

import android.os.Bundle
import com.test.xd720p.revoluttest.CurrencyApplication
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.dagger.AppComponent
import com.test.xd720p.revoluttest.networking.ApiService
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class CurrencyActivity : BaseActivity() {

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        CurrencyApplication.appComponent.inject(this)
        CoroutineScope(Dispatchers.Main).launch {
            getCurrencies()
        }
    }

    private suspend fun getCurrencies() {
        val getCurrencies = apiService.getCurrencies("EUR")
        val currencies = getCurrencies.await()
        println("base currenncy is ${currencies.baseCurrency}")
    }
}
