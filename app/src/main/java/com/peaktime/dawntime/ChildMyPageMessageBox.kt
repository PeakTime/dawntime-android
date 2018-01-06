package com.peaktime.dawntime

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by LEESANGYUN on 2018-01-05.
 */
class ChildMyPageMessageBox : Fragment(), View.OnClickListener {

    var messageList: RecyclerView? = null
    var adapter: ChildMyPageMessageBoxAdapter? = null
    var messageBoxData: ArrayList<ChildMyPageMessageBoxData>? = null

    var networkService: NetworkService? = null
    var messageBoxList: ArrayList<MessageBoxList>? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.child_mypage_messagebox, container, false)

        messageList = v.findViewById(R.id.message_list)

        messageList!!.layoutManager = LinearLayoutManager(activity)

        //requestManager = Glide.with(this)
        getMessageBoxList()

        messageBoxData = ArrayList<ChildMyPageMessageBoxData>()

        for (i in 0..messageBoxList!!.size) {
            var list = messageBoxList!!.get(i)
            var str = list.msg_date.split(" ")
            messageBoxData!!.add(ChildMyPageMessageBoxData(str[1], str[0], list.board_title, list.msg_content!!))
        }

//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "three", "삼", "샐리"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
//        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))

        CommonData.messageBoxData = messageBoxData!!

        adapter = ChildMyPageMessageBoxAdapter(messageBoxData)
        adapter!!.setOnItemClickListener(this)
        messageList!!.adapter = adapter

        return v
    }

    fun getMessageBoxList() {//SharedPreferInstance.getInstance(activity).getPreferString("EMAIL")!!
        var getContentList = networkService!!.getMessageBoxList("1")

        getContentList.enqueue(object : Callback<MessageBoxResponse> {
            override fun onResponse(call: Call<MessageBoxResponse>?, response: Response<MessageBoxResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().status.equals("success")) {

                        Log.i("status", "success")
                        messageBoxList = response.body().result
                    }
                } else {
                    Log.i("status", "fail")
                }

            }

            override fun onFailure(call: Call<MessageBoxResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }


    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}