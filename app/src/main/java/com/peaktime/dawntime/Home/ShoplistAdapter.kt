package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

/**
 * Created by minhyoung on 2018. 1. 1..
 */
class ShoplistAdapter(var dataList : ArrayList<ShoplistData>?,var requestManager: RequestManager,var user_blind : Int) : RecyclerView.Adapter<ShoplistViewHolder>() {

    private val blindImg = listOf(R.drawable.view_blind_green,R.drawable.view_blind_orange,R.drawable.view_blind_pink, R.drawable.view_blind_purple)
    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: ShoplistViewHolder?, position: Int) {
        if(user_blind == 0) {
            requestManager.load(dataList!!.get(position).goods_image).into(holder!!.shoplistImg)
        }
        else{
            holder!!.shoplistImg.setImageResource(blindImg.get(Random().nextInt(4)))
        }
        holder!!.shoplistImg.clipToOutline = true
        holder!!.shoplistText.text = cutString(dataList!!.get(position).goods_name)
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