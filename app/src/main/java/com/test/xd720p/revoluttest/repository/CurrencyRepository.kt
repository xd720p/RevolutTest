package com.test.xd720p.revoluttest.repository

import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.networking.ApiService

class CurrencyRepository constructor(private val apiService: ApiService) {


    suspend fun loadCurrencies(baseCurrency: String): CurrencyRate? {

//        CoroutineScope(Dispatchers.Main).launch {
        val request = apiService.getCurrencies(baseCurrency)
        val response = request.await()

        if (response.isSuccessful) {
            return response.body()
        }

        return null
//        }
    }

}
