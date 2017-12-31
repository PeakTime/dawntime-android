package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R


class ShopViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){

    var shopImage : ImageView = itemView!!.findViewById<ImageView>(R.id.goods_img)

    var shopName : TextView = itemView!!.findViewById<TextView>(R.id.goods_name)

    var shopPrice : TextView = itemView!!.findViewById<TextView>(R.id.goods_price)

}