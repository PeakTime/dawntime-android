package com.peaktime.dawntime.Column

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 3..
 */
class ColumnListAdapter(var dataList : ArrayList<ColumnListData>?) : RecyclerView.Adapter<ColumnListViewHolder>() {
    private var onItemClick : View.OnClickListener? = null
    override fun onBindViewHolder(holder: ColumnListViewHolder?, position: Int) {
        //holder!!.columnImg.setImageResource(dataList!!.get(position).columnImg)
        holder!!.columnImg.setBackgroundResource(dataList!!.get(position).columnImg)
        holder!!.columnText1.text = dataList!!.get(position).columnText1
        holder!!.columnText2.text = dataList!!.get(position).columnText2
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ColumnListViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.column_list_item,parent,false)
        mainView.setOnClickListener(onItemClick)
        return ColumnListViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}