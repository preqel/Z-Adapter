package com.preqel.adapterz

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.preqel.adapterz.base.ViewHolder

/**
 * Author: preqel
 * Created on: 2019-10-06.
 * Description:
 */
open class MultiItemTypeAdapter<T>(open    var mContext: Context, open  var mDatas: List<T>) :
    RecyclerView.Adapter<ViewHolder>() {

    protected lateinit var mOnItemClickListener: OnItemClickListener

    protected lateinit var mItemViewDelegateManager: ItemViewDelegateManager<T>

    init {
        mItemViewDelegateManager = ItemViewDelegateManager<T>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType)
        val layoutId = itemViewDelegate.getItemViewLayoutId()
        val holder = ViewHolder.createViewHolder(mContext, parent, layoutId)
        onViewHolderCreated(holder, holder.getConvertView())
        setListener(parent, holder, viewType)
        return holder

    }

    fun getDatas(): List<T> {
        return mDatas
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        convert(holder, mDatas[position])
    }

    public interface OnItemClickListener {
        abstract fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int)

        abstract fun onItemLongClick(
            view: View,
            holder: RecyclerView.ViewHolder,
            position: Int
        ): Boolean
    }

    override fun getItemViewType(position: Int): Int {
        return if (!useItemViewDelegateManager()) super.getItemViewType(position)
        else mItemViewDelegateManager.getItemViewType(
            mDatas[position], position
        )
    }


    protected fun setListener(parent: ViewGroup, viewHolder: ViewHolder, viewType: Int) {
        if (!isEnabled(viewType)) return
        viewHolder.getConvertView().setOnClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.adapterPosition
                mOnItemClickListener.onItemClick(v, viewHolder, position)
            }
        }

        viewHolder.getConvertView().setOnLongClickListener(View.OnLongClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.adapterPosition
                return@OnLongClickListener mOnItemClickListener.onItemLongClick(
                    v,
                    viewHolder,
                    position
                )
            }
            false
        })
    }

    protected fun isEnabled(viewType: Int): Boolean {
        return true
    }

    fun convert(holder: ViewHolder, t: T) {
        mItemViewDelegateManager.convert(holder, t, holder.adapterPosition)
    }

    fun onViewHolderCreated(holder: ViewHolder, itemView: View) {

    }


    protected fun useItemViewDelegateManager(): Boolean {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0
    }


    fun addItemViewDelegate(itemViewDelegate: ItemViewDelegate<T>): MultiItemTypeAdapter<*> {
        mItemViewDelegateManager.addDelegate(itemViewDelegate)
        return this
    }

}