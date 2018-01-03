package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_community_detail.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R.id.community_backbtn


class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        //view

//        community_gitar.setOnClickListener {
//setOnClickListener            val popup = PopupMenu(this, View.inflate(this, R.layout.activity_community_detail, ))//v는 클릭된 뷰를 의미
//            val inflater = activity.menuInflater
//            activity.menuInflater.inflate(R.menu.community_menu, popup.getMenu())
//            //popup.setOnMenuItemClickListener(listener)
//            popup.show()
////
//        }
        var community_unstarContents : ImageView?= null
        var community_unfireContents : ImageView ?= null
        var unstarContents : TextView ?= null
        var unfireContents : TextView ?= null

        community_unstarContents = findViewById(R.id.community_unstarContents)
        community_unfireContents = findViewById(R.id.community_unfireContents)
        unstarContents = findViewById(R.id.unfireContents)
        unfireContents = findViewById(R.id.unstarContents)

        /*community_unstarContents!!.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View, event: MotionEvent): Boolean {

                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {
                        unstarContents!!.text = unstarContents!!.text.toString() +1

                    }

                    MotionEvent.ACTION_MOVE -> {

                    }

                    MotionEvent.ACTION_CANCEL -> if (!mHasPerformedLongPress) {
                        // This is a tap, so remove the longpress check
                        removeLongPressCallback()
                    }

                    MotionEvent.ACTION_UP -> {
                        Log.d("CLICK", "ACTION_UP")

                        if (!mHasPerformedLongPress) {
                            // Long Click을 처리되지 않았으면 제거함.
                            removeLongPressCallback()

                            // Short Click 처리 루틴을 여기에 넣으면 됩니다.
                            performOneClick()

                        }
                    }

                    else -> {
                    }
                }

                return false
            }
        })*/
        /*
        community_backbtn!!.setOnClickListener{
            finish()
        }*/

    }
}
