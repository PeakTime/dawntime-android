package com.peaktime.dawntime.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.peektime_items.view.*

/**
 * Created by minhyoung on 2018. 1. 8..
 */
class PeaktimeGridAdapter(var dataList : ArrayList<PeektimeData>?) : BaseAdapter() {
    private var onItemClick : View.OnClickListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.peektime_items,parent,false)
        mainView!!.peektime_item_layout.setBackgroundResource(dataList!!.get(position).peektimeBackground)
        mainView!!.peektime_item_title.text = dataList!!.get(position).peektimeTitle
        mainView!!.peektime_item_text.text = dataList!!.get(position).peektimeText
        mainView!!.like_cnt_text.text = dataList!!.get(position).peektimeLikeCnt.toString()
        mainView!!.comment_cnt_text.text = dataList!!.get(position).peektimeCommentCnt.toString()
        mainView!!.scrap_cnt_text.text = dataList!!.get(position).peektimeScrapCnt.toString()
        mainView!!.setOnClickListener(onItemClick)

        return mainView
    }

    override fun getItem(position: Int): Any {
        return dataList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position as Long
    }

    override fun getCount(): Int = dataList!!.size

    fun setOnItemClick(l: View.OnClickListener?){
        onItemClick = l
    }
}