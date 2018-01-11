package com.peaktime.dawntime.MyPage

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by 예은 on 2018-01-12.
 */
class MypageMycommentViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var communityImage : ImageView = itemView!!.findViewById(R.id.communityimage)
    var horesHead : TextView = itemView!!.findViewById(R.id.horsehead)
    var contents : TextView = itemView!!.findViewById(R.id.contents)

}