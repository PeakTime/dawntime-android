package com.peaktime.dawntime.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.peaktime.dawntime.Community.CommunityLikeResponse
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.mypage_msg_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by HYEON on 2018-01-04.
 */
class MypageMsgFragment : Fragment() {

    var networkService: NetworkService? = null

    var basicLayout: LinearLayout? = null
    var index: Int? = null

    var messageList: ArrayList<MypageMessageDetailData>? = null
    var contentEdit: EditText? = null
    var scroll: ScrollView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.mypage_msg_fragment, container, false)

        contentEdit = v.findViewById(R.id.messagebox_detail_edit)
        scroll = v.findViewById(R.id.messagebox_detail_scroll)

        if (arguments != null) {
            index = arguments.getInt("index")
        }

        basicLayout = v.findViewById(R.id.messagebox_detail_basic)
        networkService = ApplicationController.instance!!.networkService

        v.messagebox_detail_back!!.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        v.messagebox_detail_btn!!.setOnClickListener {

            if (contentEdit!!.text.toString() != null) {
                sendMessageDetail()
            }
        }

        getMessageDetailList()

        return v
    }

    fun sendMessageDetail() {
        var getContentList = networkService!!.sendMessageDetail(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index!!, contentEdit!!.text.toString())

        getContentList.enqueue(object : Callback<CommunityLikeResponse> {
            override fun onResponse(call: Call<CommunityLikeResponse>?, response: Response<CommunityLikeResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {
                        var ft = fragmentManager.beginTransaction()
                        ft.detach(this@MypageMsgFragment).attach(this@MypageMsgFragment).commit()
                        scroll!!.fullScroll(View.FOCUS_DOWN)
                        contentEdit!!.setText("")
                    }
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

    fun getMessageDetailList() {
        var getContentList = networkService!!.getMessgaeDetailList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index!!)

        getContentList.enqueue(object : Callback<MypageMessageDetailResponse> {
            override fun onResponse(call: Call<MypageMessageDetailResponse>?, response: Response<MypageMessageDetailResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {
                        messageList = response.body().result

                        //동적 뷰 추가
                        for (i in 0..messageList!!.size - 1) {
                            var inflater = layoutInflater
                            if (messageList!!.get(i).user_send == true) {
                                var itemLayout = inflater.inflate(R.layout.messagebox_detail_item_me, null)
                                var text: TextView = itemLayout.findViewById(R.id.me_text)
                                text.text = messageList!!.get(i).msg_content
                                basicLayout!!.addView(itemLayout)
                            } else {
                                var itemLayout = inflater.inflate(R.layout.messagebox_detail_item_you, null)
                                var text: TextView = itemLayout.findViewById(R.id.you_text)
                                text.text = messageList!!.get(i).msg_content
                                basicLayout!!.addView(itemLayout)
                            }
                        }
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<MypageMessageDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
}