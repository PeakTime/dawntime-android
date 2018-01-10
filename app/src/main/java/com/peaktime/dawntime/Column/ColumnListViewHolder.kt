package com.peaktime.dawntime.Column

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 3..
 */
class ColumnListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var columnImg : ImageView = itemView!!.findViewById(R.id.column_img)
}