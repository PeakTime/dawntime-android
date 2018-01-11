package com.peaktime.dawntime.MyPage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.child_mypage_messagebox.view.*
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

        v.back_btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        return v
    }

    fun getList() {
        var getContentList = networkService!!.getMessageBoxList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)

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
        val fm = fragmentManager.beginTransaction()
        val fragment = MypageMsgFragment()
        val bundle = Bundle()
        bundle.putInt("index", messageBoxList!!.get(messageList!!.getChildAdapterPosition(v!!)).room_id)
        fragment.arguments = bundle
        fm.add(R.id.messagebox_detail_container, fragment, "msg_detail")
        fm.addToBackStack(null)
        fm.commit()
    }


}