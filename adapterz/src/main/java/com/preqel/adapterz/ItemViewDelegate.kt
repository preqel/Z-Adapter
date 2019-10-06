package com.preqel.adapterz

import com.preqel.adapterz.base.ViewHolder

/**
 * Author: preqel
 * Created on: 2019-10-06.
 * Description:
 */
interface ItemViewDelegate <T> {

    abstract fun getItemViewLayoutId(): Int

    abstract fun isForViewType(item: T, position: Int): Boolean

    abstract fun convert(holder: ViewHolder, t: T, position: Int)

}