package com.peaktime.dawntime.MyPage

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.child_mypage_messagebox, container, false)

        messageList = v.findViewById(R.id.message_list)

        messageList!!.layoutManager = LinearLayoutManager(activity)

        networkService = ApplicationController.instance!!.networkService

        messageBoxData = ArrayList<ChildMyPageMessageBoxData>()

        getList()

//        back_btn.setOnClickListener {
//            val fm = activity.fragmentManager
//            val transacton = fm.beginTransaction()
//            transacton.remove(this)
//            transacton.commit()
//        }

//        for (i in 0..messageBoxList!!.size-1) {
//            var list = messageBoxList!!.get(i)
//            var str = list.msg_date.split(" ")
//            messageBoxData!!.add(ChildMyPageMessageBoxData(str[1], str[0], list.board_title, list.msg_content!!))
//        }
//
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "three", "삼", "샐리"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
////        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
//
//        CommonData.messageBoxData = messageBoxData!!
//
//        adapter = ChildMyPageMessageBoxAdapter(messageBoxData)
//        adapter!!.setOnItemClickListener(this)
//        messageList!!.adapter = adapter

        return v
    }

    fun getList() {//SharedPreferInstance.getInstance(activity).getPreferString("EMAIL")!!
        var getContentList = networkService!!.getMessageBoxList("2")

        getContentList.enqueue(object : Callback<MessageBoxResponse> {
            override fun onResponse(call: Call<MessageBoxResponse>?, response: Response<MessageBoxResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {

                        Log.i("status", "success")
                        Log.i("size: ", response.body().result.toString())

                        messageBoxList = response.body().result
                        for (i in 0..messageBoxList!!.size - 1) {
                            var list = messageBoxList!!.get(i)
                            var str = list.msg_date.split("T")
                            var str_time = str[1].split(".")
                            messageBoxData!!.add(ChildMyPageMessageBoxData(str[0], str_time[0], list.board_title, list.msg_content))
                        }
                        CommonData.messageBoxData = messageBoxData!!

                        adapter = ChildMyPageMessageBoxAdapter(messageBoxData)
                        adapter!!.setOnItemClickListener(this@ChildMyPageMessageBox)
                        messageList!!.adapter = adapter
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
        Toast.makeText(activity, "클릭", Toast.LENGTH_SHORT).show()
    }


}