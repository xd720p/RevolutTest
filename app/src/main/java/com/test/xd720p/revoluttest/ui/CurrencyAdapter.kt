package com.test.xd720p.revoluttest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyAdapter(private val currencyList: MutableList<CurrencyRateVO> = ArrayList()) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setData(currencyList[position])
    }

    override fun getItemCount(): Int = currencyList.size

    fun getData(): MutableList<CurrencyRateVO> {
        return currencyList
    }

    //    FIXME maybe this shouldn't be in adapter, it should be in view model
    fun getMainCurrencyIso(): String {
        return if (currencyList.isNotEmpty()) {
            currencyList.first().iso
        } else "EUR"
    }

    //fixme use diff utils
    fun setCurrencyList(newCurrencyList: List<CurrencyRateVO>) {
        currencyList.clear()
        currencyList.addAll(newCurrencyList)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(currencyRateVO: CurrencyRateVO) {
            itemView.currencyISO.text = currencyRateVO.iso
            itemView.currencyName.text = currencyRateVO.name
            itemView.moneyValue.setText(currencyRateVO.value.toString(), TextView.BufferType.EDITABLE)
        }

    }

}