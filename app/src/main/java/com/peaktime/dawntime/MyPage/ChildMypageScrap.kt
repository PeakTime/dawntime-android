package com.peaktime.dawntime.MyPage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Community.CommunityAdapter
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.Community.CommunityList
import com.peaktime.dawntime.Community.CommunityResponse
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.child_mypage_scrap.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 예은 on 2018-01-11.
 */
class ChildMypageScrap: Fragment(), View.OnClickListener {
    private var scrapList: RecyclerView? = null
    private var adapter: CommunityAdapter? = null
    private var scrapData: ArrayList<CommunityList>? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.child_mypage_scrap, container, false)

        scrapList = v.findViewById(R.id.scrap_list)
        scrapList!!.layoutManager = LinearLayoutManager(activity)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        //뒤로가기
        v.scrap_back_btn.setOnClickListener{
            fragmentManager.popBackStack()
        }
        val fm = fragmentManager
        fm.addOnBackStackChangedListener {
            try {
                getScrapList()
            } catch (e: Exception) {
            }
        }

        getScrapList()
        return v
    }

    fun getScrapList() {
        var getContentList = networkService!!.getScrapList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<CommunityResponse> {
            override fun onResponse(call: Call<CommunityResponse>?, response: Response<CommunityResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {

                        scrapData = response.body().result

                        CommonData.communityDatas = scrapData!!

                        adapter = CommunityAdapter(scrapData, requestManager!!)
                        adapter!!.setOnItemClickListener(this@ChildMypageScrap)
                        scrapList!!.adapter = adapter
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
        bundle.putInt("index", CommonData.communityDatas!!.get(scrapList!!.getChildAdapterPosition(p0!!)).board_id)
        fragment.arguments = bundle
        fm.add(R.id.mypage_scrap_container,fragment,"detail")
        fm.addToBackStack(null)
        fm.commit()
    }

}