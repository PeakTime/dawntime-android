package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_community_detail.*

class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        community_backbtn!!.setOnClickListener{
            finish()
        }

    }
}
