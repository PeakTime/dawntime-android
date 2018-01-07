package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by minhyoung on 2018. 1. 1..
 */
class ShoplistAdapter(var dataList : ArrayList<ShoplistData>?) : RecyclerView.Adapter<ShoplistViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: ShoplistViewHolder?, position: Int) {
        holder!!.shoplistImg.setImageResource(dataList!!.get(position).shoplistImg)
        holder!!.shoplistImg.clipToOutline = true
        holder!!.shoplistText.text = dataList!!.get(position).shoplistText
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShoplistViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.shoplist_items,parent,false)
        mainView.setOnClickListener(onItemClick)
        return ShoplistViewHolder(mainView)
    }

    fun setOnClickListener(l:View.OnClickListener){
        onItemClick = l
    }

}