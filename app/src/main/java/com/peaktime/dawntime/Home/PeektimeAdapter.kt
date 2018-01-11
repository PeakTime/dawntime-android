package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Community.CommunityDetailData
import com.peaktime.dawntime.Community.CommunityList
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 2..
 */
class PeektimeAdapter(var dataList : ArrayList<CommunityList>? ) : RecyclerView.Adapter<PeektimeViewHolder>() {

    private val backGround = listOf(R.drawable.view_peakillu1_purple,R.drawable.view_peakillu2_green,
            R.drawable.view_peakillu3_violet,R.drawable.view_peakillu4_blue)
    private var count = 0

    private var onItemClick : View.OnClickListener? = null
    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: PeektimeViewHolder?, position: Int) {
        holder!!.peektimeBack.setBackgroundResource(backGround.get((count%4)))
        count++
        holder!!.peektimeTitle.text = cutString(dataList!!.get(position).board_title)
        holder!!.peektimeText.text = dataList!!.get(position).board_content
        holder!!.peektimeLikeCnt.text = dataList!!.get(position).board_like.toString()
        holder!!.peektimeCommentCnt.text = dataList!!.get(position).com_count.toString()
        holder!!.peektimeScrapCnt.text = dataList!!.get(position).scrap_count.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PeektimeViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.peektime_items,parent,false)
        mainView.setOnClickListener(onItemClick)
        return PeektimeViewHolder(mainView)
    }

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