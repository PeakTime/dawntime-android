package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.activity_community_msg.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommunityMsgActivity : AppCompatActivity() {

    var board_id: Int? = null
    var networkService: NetworkService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_msg)

        board_id = intent.getIntExtra("BOARD_ID", 0)
        networkService = ApplicationController.instance!!.networkService
        community_backbtn2.setOnClickListener {
            finish()
        }
        sendcomplte.setOnClickListener {
            communityMessage()
        }
    }

    fun communityMessage() {

        var getContentList = networkService!!.communityMessage(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!, board_id!!, contents.text.toString())

        getContentList.enqueue(object : Callback<CommunityLikeResponse> {
            override fun onResponse(call: Call<CommunityLikeResponse>?, response: Response<CommunityLikeResponse>?) {

                if (response!!.isSuccessful) {
                    ApplicationController.instance!!.makeToast("쪽지가 보내졌습니다.")
                    finish()

                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

}
