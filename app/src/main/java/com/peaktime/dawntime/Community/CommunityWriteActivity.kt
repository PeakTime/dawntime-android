package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_community_write.*


class CommunityWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write)

        community_backbtn1!!.setOnClickListener{
            finish()
        }


    }
}