package com.test.xd720p.revoluttest.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.test.xd720p.revoluttest.CurrencyApplication
import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.repository.CurrencyRepository
import javax.inject.Inject


class CurrencyViewModel constructor(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var currencyRepository: CurrencyRepository

    private val currenciesVOLiveData: MutableLiveData<CurrencyRateVO> = MutableLiveData()
    private val currenciesLiveData: LiveData<CurrencyRate>

    init {
        CurrencyApplication.appComponent.inject(this)
        currenciesLiveData = currencyRepository.getCurrencies()
    }

    fun geCurrenciesObservalbe() : LiveData<CurrencyRate> {
        currencyRepository.loadCurrencies("EUR")
        return currenciesLiveData
    }

}