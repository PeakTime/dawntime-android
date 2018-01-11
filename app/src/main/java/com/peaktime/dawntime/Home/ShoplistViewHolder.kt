package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.shoplist_items.view.*

/**
 * Created by minhyoung on 2018. 1. 1..
 */
class ShoplistViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var shoplistImg : ImageView = itemView!!.findViewById(R.id.shoplist_real_img)
    var shoplistText : TextView = itemView!!.findViewById(R.id.shoplist_text)
}