package com.test.xd720p.revoluttest.ui

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.viewmodels.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : BaseActivity() {

    private lateinit var viewModel: AndroidViewModel
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)


        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        val currenciesLiveData: LiveData<MutableList<CurrencyRateVO>> = (viewModel as CurrencyViewModel).getCurrenciesObservalbe()

        currenciesLiveData.observe(this, Observer {currencyRateVoList ->
            currencyRateVoList?.let { initCurrencyRecyclerView(it) }
        })
    }

    private fun initCurrencyRecyclerView(currencyRateVOList: MutableList<CurrencyRateVO>) {
        currencyAdapter = CurrencyAdapter(currencyRateVOList)
        currencyRecyclerView.layoutManager = LinearLayoutManager(this)
        currencyRecyclerView.adapter = currencyAdapter
    }

    private fun updateAdapter(currencyRateVOList: List<CurrencyRateVO>?) {

    }
}
