package com.peaktime.dawntime.Community

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.fragment_community.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by HYEON on 2017-12-31.
 */
class CommunityFragment : Fragment(), View.OnClickListener {

    private var communityList: RecyclerView? = null
    private var communityDatas: ArrayList<CommunityList>? = null
    private var adapter: CommunityAdapter? = null

    private var community_write: ImageButton? = null
    private var communtiy_detail : Button? = null
    private var community_search : ImageButton? =null

    private var tagList: ArrayList<String>? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_community, container, false)
        Log.i("토큰", SharedPreferInstance.getInstance(activity).getPreferString("TOKEN"))
        communityList = v.findViewById(R.id.main_list)
        community_write = v.findViewById(R.id.community_write)
        community_search = v.findViewById(R.id.community_search)

        val community_detail_layout : RelativeLayout= v.findViewById(R.id.community_cate_tab)

        tagList = ArrayList<String>()
        communityList!!.layoutManager = LinearLayoutManager(activity)
        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getList()

        //말머리 보이기
        communtiy_detail = v.findViewById(R.id.community_detail)
        communtiy_detail!!.setOnClickListener{
            communtiy_detail!!.visibility = View.GONE
            community_search!!.visibility = View.INVISIBLE
            community_write!!.visibility = View.INVISIBLE
            community_detail_layout.visibility = View.VISIBLE

            var cate_hide : Button ?= null
            cate_hide = v.findViewById(R.id.community_cate_hide)
            cate_hide!!.setOnClickListener {
               community_detail_layout.visibility = View.GONE
                communtiy_detail!!.visibility = View.VISIBLE
                community_search!!.visibility = View.VISIBLE
                community_write!!.visibility = View.VISIBLE

//                Log.i("taggg",tagList.toString())
                if (tagList!!.size == 0) {
                    getList()
//                    Log.i("사이즈0일때_","sdjfksdljfk")
                } else if (tagList!!.size == 1) {
                    tagList!!.add("default")
                    tagSearch()
                    tagList!!.remove("default")
//                    Log.i("사이즈 1일떄",tagList.toString())
                } else {
                    tagSearch()
//                    Log.i("다수일때",tagList.toString())
                }
            }
        }

//        //글 작성
        community_write!!.setOnClickListener {

            var intent = Intent(activity, CommunityWriteActicity::class.java)
            startActivityForResult(intent, 0)
        }

        //글 검색
        v.community_search.setOnClickListener {

            val fm = fragmentManager.beginTransaction()
            fm.add(R.id.community_container,CommunitySearchFragment(),"detail")
            fm.addToBackStack(null)
            fm.commit()
        }


        v.horsehead_0.setOnClickListener{
            v.horsehead_0!!.isSelected = !v.horsehead_0.isSelected
            if (v.horsehead_0.isSelected) {
                tagList!!.add(v.horsehead_0.text.toString())
            } else {
                tagList!!.remove(v.horsehead_0.text.toString())
            }
        }
        v.horsehead_1.setOnClickListener{
            v.horsehead_1!!.isSelected = !v.horsehead_1.isSelected
            if (v.horsehead_1.isSelected) {
                tagList!!.add(v.horsehead_1.text.toString())
            } else {
                tagList!!.remove(v.horsehead_1.text.toString())
            }
        }
        v.horsehead_2.setOnClickListener{
            v.horsehead_2!!.isSelected = !v.horsehead_2.isSelected
            if (v.horsehead_2.isSelected) {
                tagList!!.add(v.horsehead_2.text.toString())
            } else {
                tagList!!.remove(v.horsehead_2.text.toString())
            }
        }
        v.horsehead_3.setOnClickListener {
            v.horsehead_3!!.isSelected = !v.horsehead_3.isSelected
            if (v.horsehead_3.isSelected) {
                tagList!!.add(v.horsehead_3.text.toString())
            } else {
                tagList!!.remove(v.horsehead_3.text.toString())
            }
        }
        v.horsehead_4.setOnClickListener {
            v.horsehead_4!!.isSelected = !v.horsehead_4.isSelected
            if (v.horsehead_4.isSelected) {
                tagList!!.add(v.horsehead_4.text.toString())
            } else {
                tagList!!.remove(v.horsehead_4.text.toString())
            }
        }
        v.horsehead_5.setOnClickListener {
            v.horsehead_5!!.isSelected = !v.horsehead_5.isSelected
            if (v.horsehead_5.isSelected) {
                tagList!!.add(v.horsehead_5.text.toString())
            } else {
                tagList!!.remove(v.horsehead_5.text.toString())
            }
        }
        v.horsehead_6.setOnClickListener {
            v.horsehead_6!!.isSelected = !v.horsehead_6.isSelected
            if (v.horsehead_6.isSelected) {
                tagList!!.add(v.horsehead_6.text.toString())
            } else {
                tagList!!.remove(v.horsehead_6.text.toString())
            }
        }
        v.horsehead_7.setOnClickListener {
            v.horsehead_7!!.isSelected = !v.horsehead_7.isSelected
            if (v.horsehead_7.isSelected) {
                tagList!!.add(v.horsehead_7.text.toString())
            } else {
                tagList!!.remove(v.horsehead_7.text.toString())
            }
        }
        v.horsehead_8.setOnClickListener {
            v.horsehead_8!!.isSelected = !v.horsehead_8.isSelected
            if (v.horsehead_8.isSelected) {
                tagList!!.add(v.horsehead_8.text.toString())
            } else {
                tagList!!.remove(v.horsehead_8.text.toString())
            }
        }
        v.horsehead_9.setOnClickListener {
            v.horsehead_9!!.isSelected = !v.horsehead_9.isSelected
            if (v.horsehead_9.isSelected) {
                tagList!!.add(v.horsehead_9.text.toString())
            } else {
                tagList!!.remove(v.horsehead_9.text.toString())
            }
        }
        val fm = fragmentManager
        fm.addOnBackStackChangedListener {
            try {
                getList()
            } catch (e: Exception) {
            }
        }

        return v
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            getList()
        }
    }

    fun tagSearch() {
        var getContentList = networkService!!.tagSearch(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, tagList!!)
        getContentList.enqueue(object : Callback<TagSearchResponse> {
            override fun onResponse(call: Call<TagSearchResponse>?, response: Response<TagSearchResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {

                        communityDatas = response.body().result

                        CommonData.communityDatas = communityDatas!!

                        adapter = CommunityAdapter(communityDatas, requestManager!!)
                        adapter!!.setOnItemClickListener(this@CommunityFragment)
                        communityList!!.adapter = adapter
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<TagSearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
    fun getList() {
        var getContentList = networkService!!.getCommunityList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<CommunityResponse> {
            override fun onResponse(call: Call<CommunityResponse>?, response: Response<CommunityResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {

                        communityDatas = response.body().result

                        CommonData.communityDatas = communityDatas!!

                        adapter = CommunityAdapter(communityDatas, requestManager!!)
                        adapter!!.setOnItemClickListener(this@CommunityFragment)
                        communityList!!.adapter = adapter
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityResponse>?, t: Throwable?) {
//                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    override fun onClick(p0: View?) {

        val fm = fragmentManager.beginTransaction()
        val fragment = CommunityDetailFragment()
        val bundle = Bundle()
        bundle.putInt("index", communityDatas!!.get(communityList!!.getChildAdapterPosition(p0!!)).board_id)
        fragment.arguments = bundle
        fm.add(R.id.community_container,fragment,"detail")
        fm.addToBackStack(null)
        fm.commit()
    }
}


