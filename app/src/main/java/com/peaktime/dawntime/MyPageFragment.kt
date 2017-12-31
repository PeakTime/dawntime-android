package com.peaktime.dawntime

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mypage.view.*

class MyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage,container,false)
        if(arguments != null){
            v!!.fourthText.text = arguments.getString("title")
        }
        return v
    }
}
