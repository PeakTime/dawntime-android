package com.peaktime.dawntime.Community

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.peaktime.dawntime.R

/**
 * Created by 예은 on 2018-01-03.
 */
class CommunitySearchFragment :Fragment() {
    private var communitySearchCancelBtn : ImageButton?= null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.activity_community_search,container,false)

        communitySearchCancelBtn = v.findViewById(R.id.communitySearchCancelBtn)
        communitySearchCancelBtn!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fragment = CommunityFragment()
            transacton.remove(this)
            transacton.commit()
        }
        return v
    }
}