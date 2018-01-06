package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 6..
 */
class TakeImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var takeImageView : ImageView = itemView!!.findViewById(R.id.community_write_imageview)
}