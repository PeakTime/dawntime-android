package com.peaktime.dawntime.Shop.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-02.
 */
class SecondFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.pager_item, container, false)
        val image = rootView!!.findViewById<View>(R.id.imageView) as ImageView

        if(arguments!=null){
            Glide.with(this).load(arguments.getString("image")).into(image)
        }else
            image.setBackgroundResource(R.drawable.selly)

        return rootView

    }
}