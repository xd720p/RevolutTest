package com.test.xd720p.revoluttest.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import com.test.xd720p.revoluttest.utils.diffutils.CurrencyDiffUtilCallback
import com.test.xd720p.revoluttest.viewmodels.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : BaseActivity() {

    private lateinit var viewModel: CurrencyViewModel
    private lateinit var currencyAdapter: CurrencyAdapter
    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            viewModel.updateCurrencies(currencyAdapter.getMainCurrencyVO())
            handler.postDelayed(this, 1000)
        }
    }

    private val recyclerViewItemChanged: (CurrencyRateVO, Int) -> Unit = { currencyRateVO, itemPosition ->
//        onBaseCurrencyChanged()
//        currencyRecyclerView.smoothScrollToPosition(0)
    }

    private fun onBaseCurrencyChanged() {
//        handler.removeCallbacksAndMessages(runnable)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        initCurrencyRecyclerView()
    }

    private fun initCurrencyRecyclerView() {
        currencyAdapter = CurrencyAdapter(recyclerViewItemChanged)
        currencyRecyclerView.layoutManager = LinearLayoutManager(this)
        (currencyRecyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
        currencyRecyclerView.adapter = currencyAdapter
    }

    override fun onStart() {
        super.onStart()
        subScribeOnCurrencyUpdated()
    }

    private fun subScribeOnCurrencyUpdated() {
        val currenciesLiveData: LiveData<List<CurrencyRateVO>> = viewModel.getCurrenciesObservable()
        currenciesLiveData.observe(this, Observer {currencyRateVoList ->
            currencyRateVoList?.let { updateAdapter(it) }
        })
        handler.post(runnable)
    }

    private fun updateAdapter(currencyRateVOList: List<CurrencyRateVO>) {
        val currenciesDiffUtilCallback = CurrencyDiffUtilCallback(currencyAdapter.getData(), currencyRateVOList)
        val currenciesDiffResult = DiffUtil.calculateDiff(currenciesDiffUtilCallback)

        currencyAdapter.setCurrencyList(currencyRateVOList)
        currenciesDiffResult.dispatchUpdatesTo(currencyAdapter)
    }

    override fun onStop() {
        handler.removeCallbacks(runnable)
        super.onStop()
    }
}
