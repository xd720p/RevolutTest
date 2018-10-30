package com.test.xd720p.revoluttest.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.networking.ApiService
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch

class CurrencyRepository constructor(private val apiService: ApiService) {

    private val currencyRateData: MutableLiveData<CurrencyRate> = MutableLiveData()

    fun getCurrencies(): LiveData<CurrencyRate> {
        return currencyRateData
    }

    fun loadCurrencies(baseCurrency: String) {

        CoroutineScope(Dispatchers.Main).launch {
            val request = apiService.getCurrencies(baseCurrency)
            val response = request.await()

            if (response.isSuccessful) {
//                FIXME WTF IS POSTVALUE
                currencyRateData.postValue(response.body())
            }
        }
    }

}
