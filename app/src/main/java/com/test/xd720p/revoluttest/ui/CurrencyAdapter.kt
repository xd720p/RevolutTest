package com.test.xd720p.revoluttest.ui

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.xd720p.revoluttest.R
import com.test.xd720p.revoluttest.data.CurrencyRateVO
import kotlinx.android.synthetic.main.currency_item.view.*
import java.util.*

class CurrencyAdapter(private val onItemChanged: (CurrencyRateVO, Int) -> Unit, private val currencyList: MutableList<CurrencyRateVO> = ArrayList()) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setData(currencyList[position])
        holder.setMoneyValueIsEnabled(position == 0)
    }

    override fun getItemCount(): Int = currencyList.size

    fun getData(): MutableList<CurrencyRateVO> {
        return currencyList
    }

    //    FIXME maybe this shouldn't be in adapter, it should be in view model
    fun getMainCurrencyVO(): CurrencyRateVO {
        return if (currencyList.isNotEmpty()) {
            currencyList.first()
        } else CurrencyRateVO("EUR", "EUR", 1f)
    }

    fun setCurrencyList(newCurrencyList: List<CurrencyRateVO>) {
        currencyList.clear()
        currencyList.addAll(newCurrencyList)
    }

    private fun moveItemToTop(itemPosition: Int) {
        Collections.swap(currencyList, itemPosition, 0)
        notifyItemMoved(itemPosition, 0)

        onItemChanged(currencyList.first(), 0)
    }

    private fun changeItemMoneyValue(currencyRateVO: CurrencyRateVO) {
        onItemChanged(currencyRateVO, 0)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(currencyRateVO: CurrencyRateVO) {
            itemView.currencyISO.text = currencyRateVO.iso
            itemView.currencyName.text = currencyRateVO.name
            itemView.moneyValue.setText(currencyRateVO.value.toString(), TextView.BufferType.EDITABLE)
        }

        fun setMoneyValueIsEnabled(isEnabled: Boolean) {
            itemView.moneyValue.isClickable = isEnabled
            itemView.moneyValue.isFocusable = isEnabled
        }

    }

}