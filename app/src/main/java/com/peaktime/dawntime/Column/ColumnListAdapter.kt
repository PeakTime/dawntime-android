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
class ColumnListAdapter(var dataList : ArrayList<ColumnListData>?,var requestManager: RequestManager) : RecyclerView.Adapter<ColumnListViewHolder>() {
    private var onItemClick : View.OnClickListener? = null
    override fun onBindViewHolder(holder: ColumnListViewHolder?, position: Int) {
        //holder!!.columnImg.setImageResource(dataList!!.get(position).columnImg)
        requestManager.load(dataList!!.get(position).column_head).into(holder!!.columnImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ColumnListViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.column_list_item,parent,false)
        mainView.setOnClickListener(onItemClick)
        return ColumnListViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}