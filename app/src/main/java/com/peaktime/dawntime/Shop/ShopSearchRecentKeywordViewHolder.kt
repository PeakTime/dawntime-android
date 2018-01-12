package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-08.
 */
class ShopSearchRecentKeywordViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){

    var recentKeywordName : TextView = itemView!!.findViewById<TextView>(R.id.recent_keword_item)
    var deleteKeywordBtn : ImageView = itemView!!.findViewById<ImageView>(R.id.delete_btn)

}