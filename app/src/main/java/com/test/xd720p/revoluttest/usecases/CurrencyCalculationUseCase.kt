package com.test.xd720p.revoluttest.usecases

import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.repository.CurrencyRepository

class CurrencyCalculationUseCase(private val currencyRepository: CurrencyRepository) {

    suspend fun getCalculatedRates(baseCurrencyVO: CurrencyRateVO): List<CurrencyRateVO> {
        val currencyRateVOList: MutableList<CurrencyRateVO> = ArrayList()
        val loadedCurrencyRate = currencyRepository.loadCurrencies(baseCurrencyVO.iso)

        currencyRateVOList.add(baseCurrencyVO)

        loadedCurrencyRate?.currencyRates?.forEach {
            currencyRateVOList.add(CurrencyRateVO(it.key, it.key, calculateCurrencyValue(baseCurrencyVO.value, it.value)))
        }

        return currencyRateVOList
    }

    private fun calculateCurrencyValue(baseValue: Float, currencyRateValue: Float) = baseValue * currencyRateValue
}