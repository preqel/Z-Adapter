package com.preqel.adapterz

import android.content.Context
import com.preqel.adapterz.base.ViewHolder

/**
 * Author: preqel
 * Created on: 2019-10-06.
 * Description:
 */
abstract class CommonAdadpter<T>(  override var mContext: Context, val layoutid: Int, override var mDatas: List<T>) :


    MultiItemTypeAdapter<T>(mContext, mDatas) {


    init {
        addItemViewDelegate(object : ItemViewDelegate<T> {
            override fun getItemViewLayoutId(): Int = layoutid

            override fun isForViewType(item: T, position: Int): Boolean = true

            override fun convert(holder: ViewHolder, t: T, position: Int) {
               this@CommonAdadpter.convert(holder, t, position)
            }

        })
    }

    protected abstract fun convert(holder: ViewHolder, t: T, position: Int)


}