package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.peaktime.dawntime.R


/**
 * Created by HYEON on 2017-12-31.
 */
class Communitymain : AppCompatActivity(), View.OnClickListener {

    private var onItemClick : View.OnClickListener? = null

    override fun onClick(p0: View?) {

    }

    private var communityList: RecyclerView? = null
    private var communityDatas: ArrayList<CommunityData>? = null
    private var adapter: CommunityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        communityList = findViewById(R.id.main_list)
        communityList!!.layoutManager = LinearLayoutManager(this)

        communityDatas = ArrayList<CommunityData>()
        communityDatas!!.add(CommunityData(R.drawable.brown, "one", "원", "브라운"))
        communityDatas!!.add(CommunityData(R.drawable.cony, "two", "투", "코니"))
        communityDatas!!.add(CommunityData(R.drawable.selly, "three", "삼", "샐리"))

        adapter = CommunityAdapter(communityDatas)
        adapter!!.setOnItenClickListener(this)
        communityList!!.adapter = adapter



    }

}