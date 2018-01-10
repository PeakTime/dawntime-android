package com.peaktime.dawntime.Column

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 3..
 */
class ColumnAdapter(var dataList : ArrayList<ColumnData>?,var requestManager: RequestManager) : RecyclerView.Adapter<ColumnViewHolder>() {
    private var getItemClick : View.OnClickListener? = null
    override fun onBindViewHolder(holder: ColumnViewHolder?, position: Int) {
        requestManager.load(dataList!!.get(position).column_image).into(holder!!.cardNewsImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ColumnViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.column_items,parent,false)
        return ColumnViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

}