package com.peaktime.dawntime.Column

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 3..
 */
class ColumnViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardNewsImg : ImageView = itemView!!.findViewById(R.id.card_news_img)
}