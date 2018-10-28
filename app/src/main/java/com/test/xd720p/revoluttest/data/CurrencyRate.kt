package com.test.xd720p.revoluttest.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CurrencyRate(@SerializedName("base") val baseCurrency: String,
                        @SerializedName("date") val date: Date,
                        @SerializedName("rates") val currencyRates: Map<String, Float>)