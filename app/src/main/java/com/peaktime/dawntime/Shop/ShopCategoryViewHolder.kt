package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-04.
 */
class ShopCategoryViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView){

    var categoryName : Button = itemView!!.findViewById<Button>(R.id.category_name_btn)


}