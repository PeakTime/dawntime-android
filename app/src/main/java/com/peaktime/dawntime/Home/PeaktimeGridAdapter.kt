package com.peaktime.dawntime.Home

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.peektime_items.view.*
import java.util.*

/**
 * Created by minhyoung on 2018. 1. 8..
 */
class PeaktimeGridAdapter(var dataList : ArrayList<PeektimeData>?) : BaseAdapter() {
    private val backGround = listOf(R.drawable.view_peakillu1_purple,R.drawable.view_peakillu2_green,
        R.drawable.view_peakillu3_violet,R.drawable.view_peakillu4_blue)
    private var count = 0
    private var onItemClick : View.OnClickListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.peektime_items,parent,false)

        mainView!!.peektime_item_layout.setBackgroundResource(backGround.get((count%4)))
        count++
        mainView!!.peektime_item_title.text = cutString(dataList!!.get(position).board_title)
        mainView!!.peektime_item_text.text = dataList!!.get(position).board_content
        mainView!!.like_cnt_text.text = dataList!!.get(position).board_like.toString()
        mainView!!.comment_cnt_text.text = dataList!!.get(position).com_count.toString()
        mainView!!.scrap_cnt_text.text = dataList!!.get(position).scrap_count.toString()
        //mainView!!.setOnClickListener(onItemClick)

        return mainView
    }

    override fun getItem(position: Int): Any {
        return dataList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int = dataList!!.size

    fun setOnItemClick(l: View.OnClickListener?){
        onItemClick = l
    }

    fun cutString(str : String?) : String?{
        var cutStr : String? = null

        if(str!!.length > 10){
            cutStr = str!!.substring(0,10) + "..."
        }
        else{
            cutStr = str
        }

        return cutStr
    }
}