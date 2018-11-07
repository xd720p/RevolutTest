package com.test.xd720p.revoluttest.ui

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.viewmodels.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : BaseActivity() {

    private lateinit var viewModel: CurrencyViewModel
    private lateinit var currencyAdapter: CurrencyAdapter
    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            viewModel.updateCurrencies(currencyAdapter.getMainCurrencyIso())
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        initCurrencyRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        subScribeOnCurrencyUpdated()
    }

    private fun initCurrencyRecyclerView() {
        currencyAdapter = CurrencyAdapter()
        currencyRecyclerView.layoutManager = LinearLayoutManager(this)
        currencyRecyclerView.adapter = currencyAdapter
    }

    private fun subScribeOnCurrencyUpdated() {
        val currenciesLiveData: LiveData<MutableList<CurrencyRateVO>> = viewModel.getCurrenciesObservalbe()
        currenciesLiveData.observe(this, Observer {currencyRateVoList ->
            currencyRateVoList?.let { updateAdapter(it) }
        })
        handler.post(runnable)
    }

    private fun updateAdapter(currencyRateVOList: List<CurrencyRateVO>) {
        currencyAdapter.updateCurrencyList(currencyRateVOList)
    }

    override fun onStop() {
        handler.removeCallbacks(runnable)
        super.onStop()
    }
}
