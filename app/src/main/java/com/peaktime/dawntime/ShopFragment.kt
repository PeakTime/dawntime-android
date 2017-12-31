package com.peaktime.dawntime

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.fragment_shop.view.*

class ShopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_shop,container,false)
        if(arguments != null){
            v!!.thirdText.text = arguments.getString("title")
        }
        startActivity(Intent(activity,ShopActivity::class.java))
        return v
    }
}
