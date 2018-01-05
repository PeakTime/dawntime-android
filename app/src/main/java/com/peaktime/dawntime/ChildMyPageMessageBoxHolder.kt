package com.peaktime.dawntime

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * Created by LEESANGYUN on 2018-01-05.
 */
class ChildMyPageMessageBoxHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var timeText: TextView = itemView!!.findViewById(R.id.time_text)
    var dateText: TextView = itemView!!.findViewById(R.id.date_text)
    var titleText: TextView = itemView!!.findViewById(R.id.title_text)
    var contentsText: TextView = itemView!!.findViewById(R.id.content_text)
}