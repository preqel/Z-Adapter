package com.preqel.adapterz.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: preqel
 * Created on: 2019-10-06.
 * Description:
 */
class ViewHolder(val mContext: Context, val mConvertView: View) : RecyclerView.ViewHolder(mConvertView) {

    private var mViews: SparseArray<View> = SparseArray<View>()

    companion object{

        fun createViewHolder(context: Context, itemView: View): ViewHolder {
            var holder: ViewHolder = ViewHolder(context, itemView)
            return holder
        }

        fun createViewHolder(context: Context,itemView:ViewGroup, layoutId:Int) :ViewHolder{
            var itemView = LayoutInflater.from(context).inflate(layoutId , null ,false)
            var holder: ViewHolder = ViewHolder(context, itemView)
            return holder
        }

    }

    //根据id获取view
    fun <T : View> getView(viewId: Int): T {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }


    //获取convertView
    fun getConvertView(): View {
        return mConvertView
    }

}