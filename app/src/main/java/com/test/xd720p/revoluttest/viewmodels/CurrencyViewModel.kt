package com.test.xd720p.revoluttest.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.test.xd720p.revoluttest.CurrencyApplication
import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.repository.CurrencyRepository
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class CurrencyViewModel constructor(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var currencyRepository: CurrencyRepository

    private val currenciesVOLiveData: MutableLiveData<MutableList<CurrencyRateVO>> = MutableLiveData()

    init {
        CurrencyApplication.appComponent.inject(this)
    }

    fun getCurrenciesObservalbe() : LiveData<MutableList<CurrencyRateVO>> {
        return currenciesVOLiveData
    }

    fun updateCurrencies(currencyIso: String) {
        CoroutineScope(Dispatchers.Main).launch {
            updateCurrenciesVO(currencyRepository.loadCurrencies(currencyIso))
        }
    }

    fun updateCurrenciesVO(currencyRate: CurrencyRate?) {
        val currencyRateVOList: MutableList<CurrencyRateVO> = ArrayList()
        currencyRate?.baseCurrency?.let {
//            FIXME real rate pls
            currencyRateVOList.add(CurrencyRateVO(it, it, 1f))
        }
        currencyRate?.currencyRates?.forEach {
            currencyRateVOList.add(CurrencyRateVO(it.key, it.key, it.value))
        }
        currenciesVOLiveData.postValue(currencyRateVOList)
    }

}