package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.peaktime.dawntime.R

/**
 * Created by HYEON on 2018-01-05.
 */

class CommunityReplyViewholder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var replyTitle : TextView = itemView!!.findViewById(R.id.replytitle)
    var replyDate : TextView = itemView!!.findViewById(R.id.replydate)
    var replyContents : TextView = itemView!!.findViewById(R.id.replycontents)
}