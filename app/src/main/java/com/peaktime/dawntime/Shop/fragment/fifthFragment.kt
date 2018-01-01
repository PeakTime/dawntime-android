package com.peaktime.dawntime.Shop.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-02.
 */
class fifthFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.pager_item, container, false)
        val ImageView = rootView!!.findViewById<View>(R.id.imageView)
        ImageView.setBackgroundResource(R.drawable.fire)

        return rootView

    }
}