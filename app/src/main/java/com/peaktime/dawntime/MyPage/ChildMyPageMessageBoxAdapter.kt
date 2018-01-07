package com.peaktime.dawntime.MyPage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by LEESANGYUN on 2018-01-05.
 */
class ChildMyPageMessageBoxAdapter(var dataList: ArrayList<ChildMyPageMessageBoxData>?) : RecyclerView.Adapter<ChildMyPageMessageBoxHolder>() {

    private var getItemClick: View.OnClickListener? = null

    override fun getItemCount(): Int = dataList!!.size


    override fun onBindViewHolder(holder: ChildMyPageMessageBoxHolder?, position: Int) {

        holder!!.titleText.text = dataList!!.get(position).title
        holder.contentsText.text = dataList!!.get(position).contents
        holder.dateText.text = dataList!!.get(position).date
        holder.timeText.text = dataList!!.get(position).time
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChildMyPageMessageBoxHolder {

        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.child_mypage_meessagebox_item, parent, false)
        mainView.setOnClickListener(getItemClick)
        return ChildMyPageMessageBoxHolder(mainView)

    }

    fun setOnItemClickListener(click: View.OnClickListener) {
        getItemClick = click
    }
}