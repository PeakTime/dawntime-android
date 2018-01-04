package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-04.
 */
class ShopKindViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){

    var kindName : TextView = itemView!!.findViewById<TextView>(R.id.kind_name_textview)


}