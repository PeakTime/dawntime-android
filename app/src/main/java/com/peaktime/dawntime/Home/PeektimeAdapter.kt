package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 2..
 */
class PeektimeAdapter(var dataList : ArrayList<PeektimeData>? ) : RecyclerView.Adapter<PeektimeViewHolder>() {

    private var onItemClick : View.OnClickListener? = null
    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: PeektimeViewHolder?, position: Int) {
        holder!!.peektimeBack.setBackgroundResource(dataList!!.get(position).peektimeBackground)
        holder!!.peektimeTitle.text = dataList!!.get(position).peektimeTitle
        holder!!.peektimeText.text = dataList!!.get(position).peektimeText
        holder!!.peektimeLikeCnt.text = dataList!!.get(position).peektimeLikeCnt.toString()
        holder!!.peektimeCommentCnt.text = dataList!!.get(position).peektimeCommentCnt.toString()
        holder!!.peektimeScrapCnt.text = dataList!!.get(position).peektimeScrapCnt.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PeektimeViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.peektime_items,parent,false)
        mainView.setOnClickListener(onItemClick)
        return PeektimeViewHolder(mainView)
    }

    fun setOnItemClick(l: View.OnClickListener?){
        onItemClick = l
    }
}