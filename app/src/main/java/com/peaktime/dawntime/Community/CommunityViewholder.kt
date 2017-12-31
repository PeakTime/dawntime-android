package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by HYEON on 2017-12-31.
 */
class CommunityViewholder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var communityImage : ImageView = itemView!!.findViewById(R.id.communityimage)
    var horesHead : TextView = itemView!!.findViewById(R.id.horsehead)
    var title : TextView = itemView!!.findViewById(R.id.title)
    var contents : TextView = itemView!!.findViewById(R.id.contents)
}