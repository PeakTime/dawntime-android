package com.peaktime.dawntime.Community

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.peaktime.dawntime.*
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

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_community, container, false)

        communityList = v.findViewById(R.id.main_list)
        community_write = v.findViewById(R.id.community_write)
        community_search = v.findViewById(R.id.community_search)

        val community_detail_layout : RelativeLayout= v.findViewById(R.id.community_cate_tab)

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
            }
        }

        //글 작성
       community_write!!.setOnClickListener {
           val intent = Intent(activity, CommunityWriteFragment::class.java)
           startActivity(intent)
       }
        v.community_write!!.setOnClickListener {
//                val fm = activity.fragmentManager
//                val transacton = fm.beginTransaction()
//                val fragment = CommunityWriteFragment()
//                transacton.add(R.id.community_container, fragment, "write")
//                transacton.addToBackStack(null)
//                transacton.commit()
            val fm = fragmentManager.beginTransaction()
            fm.add(R.id.community_container,CommunityWriteFragment(),"write")
            fm.addToBackStack(null)
            fm.commit()
        }
//        val listener = object : OnMenuItemClickListener() {
//
//
//            fun onMenuItemClick(item: MenuItem): Boolean {
//

//                when (item.getItemId()) {
//                //눌러진 MenuItem의 Item Id를 얻어와 식별
//
//                    R.id.save ->
//                        Toast.makeText(this@MainActivity, "SAVE", Toast.LENGTH_SHORT).show()
//                    R.id.search ->
//                        Toast.makeText(this@MainActivity, "SEARCH", Toast.LENGTH_SHORT).show()
//                    R.id.setting ->
//                        Toast.makeText(this@MainActivity, "SETTING", Toast.LENGTH_SHORT).show()
//                }
//                return false
//            }
//       }
        //글 검색
        v.community_search.setOnClickListener {
//            val fm = activity.fragmentManager
//            val transacton = fm.beginTransaction()
//            val fragment = CommunitySearchFragment()
//            //val bundle = Bundle()
//            //bundle.putInt("index", communityList!!.getChildAdapterPosition(p0!!))
//            //fragment.arguments = bundle
//            transacton.add(R.id.community_container, fragment, "detial")
//            transacton.addToBackStack(null)
//            transacton.commit()
            val fm = fragmentManager.beginTransaction()
            fm.add(R.id.community_container,CommunitySearchFragment(),"detail")
            fm.addToBackStack(null)
            fm.commit()
        }
        return v
    }

    fun getList() {
        var getContentList = networkService!!.getCommunityList(1)
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
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    override fun onClick(p0: View?) {

        val fm = fragmentManager.beginTransaction()
        val fragment = CommunityDetailFragment()
        val bundle = Bundle()
        bundle.putInt("index", adapter!!.itemCount - communityList!!.getChildAdapterPosition(p0!!))
        fragment.arguments = bundle
        fm.add(R.id.community_container,fragment,"detail")
        fm.addToBackStack(null)
        fm.commit()
    }
}


