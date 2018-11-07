package com.test.xd720p.revoluttest.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.test.xd720p.revoluttest.CurrencyApplication
import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.repository.CurrencyRepository
import com.test.xd720p.revoluttest.usecases.CurrencyCalculationUseCase
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class CurrencyViewModel constructor(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var currencyCalculationUseCase: CurrencyCalculationUseCase

    private val currenciesVOLiveData: MutableLiveData<List<CurrencyRateVO>> = MutableLiveData()

    init {
        CurrencyApplication.appComponent.inject(this)
    }

    fun getCurrenciesObservable() : LiveData<List<CurrencyRateVO>> {
        return currenciesVOLiveData
    }

    fun updateCurrencies(baseCurrencyRateVO: CurrencyRateVO) {
        CoroutineScope(Dispatchers.Main).launch {
            updateCurrenciesVO(baseCurrencyRateVO)
        }
    }

    private suspend fun updateCurrenciesVO(baseCurrencyRatesVO: CurrencyRateVO) {
        val currencyRateVOList = currencyCalculationUseCase.getCalculatedRates(baseCurrencyRatesVO)

        currenciesVOLiveData.postValue(currencyRateVOList)
    }

}