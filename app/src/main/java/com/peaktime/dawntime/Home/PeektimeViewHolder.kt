package com.peaktime.dawntime.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.peektime_items.view.*

/**
 * Created by minhyoung on 2018. 1. 2..
 */
class PeektimeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var peektimeBack : RelativeLayout = itemView!!.findViewById(R.id.peektime_item_layout)
    var peektimeTitle : TextView = itemView!!.findViewById(R.id.peektime_item_title)
    var peektimeText : TextView = itemView!!.findViewById(R.id.peektime_item_text)
    var peektimeLikeCnt : TextView = itemView!!.findViewById(R.id.like_cnt_text)
    var peektimeCommentCnt : TextView = itemView!!.findViewById(R.id.comment_cnt_text)
    var peektimeScrapCnt : TextView = itemView!!.findViewById(R.id.scrap_cnt_text)
}