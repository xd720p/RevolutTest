package com.test.xd720p.revoluttest.ui

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRate
import com.test.xd720p.revoluttest.viewmodels.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : BaseActivity() {

    private lateinit var viewModel: AndroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        val currenciesLiveData: LiveData<CurrencyRate> = (viewModel as CurrencyViewModel).geCurrenciesObservalbe()

        currenciesLiveData.observe(this, Observer {currencyRateVo ->
            currencyView.text = currencyRateVo?.baseCurrency
        })
    }
}
