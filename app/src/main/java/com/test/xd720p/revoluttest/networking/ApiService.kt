package com.test.xd720p.revoluttest.networking

import com.test.xd720p.revoluttest.data.CurrencyRate
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    fun getCurrencies(@Query("base") baseCurrency: String): Deferred<Response<CurrencyRate>>

}