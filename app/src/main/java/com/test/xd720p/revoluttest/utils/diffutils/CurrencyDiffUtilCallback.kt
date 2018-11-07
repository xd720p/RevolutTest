package com.test.xd720p.revoluttest.utils.diffutils

import android.support.v7.util.DiffUtil
import com.test.xd720p.revoluttest.data.CurrencyRateVO

class CurrencyDiffUtilCallback(private val oldItems: List<CurrencyRateVO>, private val newItems: List<CurrencyRateVO>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.iso == newItem.iso
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.value == newItem.value
    }

}
