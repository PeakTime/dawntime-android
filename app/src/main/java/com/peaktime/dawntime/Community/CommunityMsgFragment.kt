package com.peaktime.dawntime.Community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.community_msg_fragment.view.*

/**
 * Created by HYEON on 2018-01-04.
 */
class CommunityMsgFragment : android.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.community_msg_fragment, container, false)



        v.community_backbtn2!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fragment = CommunityDetailFragment()
            transacton.remove(this)
            transacton.commit()
        }
        v.sendcomplte!!.setOnClickListener {
            Toast.makeText(context, "전송되었습니다.", Toast.LENGTH_SHORT).show()
        }
        return v

    }
}