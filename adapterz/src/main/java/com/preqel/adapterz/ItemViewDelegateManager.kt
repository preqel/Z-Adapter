package com.preqel.adapterz

import androidx.collection.SparseArrayCompat
import com.preqel.adapterz.base.ViewHolder
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

/**
 * Author: preqel
 * Created on: 2019-10-06.
 * Description:
 */
class ItemViewDelegateManager<T> {

    var delegates: SparseArrayCompat<ItemViewDelegate<T>> = SparseArrayCompat()

    fun getItemViewDelegateCount(): Int {
        return delegates.size()
    }


    fun addDelegate(itemviewdelegate: ItemViewDelegate<T>): ItemViewDelegateManager<T> {
        var viewtype = delegates.size()
        if (delegates != null) {
            delegates.put(viewtype, itemviewdelegate)
            viewtype++
        }
        return this
    }

    fun addDelegate(
        viewtype: Int,
        itemviewdelegate: ItemViewDelegate<T>
    ): ItemViewDelegateManager<T> {

        if (delegates.get(viewtype) != null) {
            throw IllegalArgumentException("")
        }
        delegates.put(viewtype, itemviewdelegate)
        return this
    }

    fun removeDelegate(delegate: ItemViewDelegate<T>): ItemViewDelegateManager<T> {
        if (delegates == null) {
            throw Exception("ItemViewDelegate is null")
        }
        val indextoremove = delegates.indexOfValue(delegate)
        if (indextoremove >= 0) {
            delegates.removeAt(indextoremove)
        }
        return this
    }

    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T> {
        return delegates.get(viewType)!!
    }
    fun getItemViewType(item: T, position: Int): Int {
        val delegatesCount = delegates.size()
        for (i in delegatesCount - 1 downTo 0) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i)
            }
        }
        throw IllegalArgumentException(
            "No ItemViewDelegate added that matches position=$position in data source"
        )
    }

    fun convert(holder: ViewHolder, item: T, position: Int) {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate = delegates.valueAt(i)

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position)
                return
            }
        }
        throw IllegalArgumentException(
            "No ItemViewDelegateManager added that matches position=$position in data source"
        )
    }


}