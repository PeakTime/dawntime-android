package com.peaktime.dawntime

import android.app.Fragment
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by LEESANGYUN on 2018-01-05.
 */
class ChildMyPageMessageBox : Fragment(), View.OnClickListener {

    var messageList: RecyclerView? = null
    var adapter: ChildMyPageMessageBoxAdapter? = null
    var messageBoxData: ArrayList<ChildMyPageMessageBoxData>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.child_mypage_messagebox, container, false)

        messageList = v.findViewById(R.id.message_list)

        messageList!!.layoutManager = LinearLayoutManager(activity)

        messageBoxData = ArrayList<ChildMyPageMessageBoxData>()
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "three", "삼", "샐리"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "one", "원", "브라운"))
        messageBoxData!!.add(ChildMyPageMessageBoxData("2017,12.27", "two", "투", "코니"))

        CommonData.messageBoxData = messageBoxData!!

        adapter = ChildMyPageMessageBoxAdapter(messageBoxData)
        adapter!!.setOnItemClickListener(this)
        messageList!!.adapter = adapter

        return v
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}