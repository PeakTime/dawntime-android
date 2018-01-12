package com.peaktime.dawntime.MyPage

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import com.peaktime.dawntime.Shop.ShopAdapter
import com.peaktime.dawntime.Shop.ShopBestData
import com.peaktime.dawntime.Shop.ShopBestResponse
import com.peaktime.dawntime.Shop.ShopDetailActivity
import kotlinx.android.synthetic.main.child_mypage_basket.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 예은 on 2018-01-11.
 */
class ChildMypageBasket : Fragment(), View.OnClickListener {
    private var basketList: RecyclerView? = null
    //private var adapter: ChildMypageBasketAdapter? = null
    private var adapter: ShopAdapter? = null
    private var basketData: ArrayList<ShopBestData>? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.child_mypage_basket, container, false)

        basketList = v.findViewById(R.id.basket_list)
        basketList!!.layoutManager = GridLayoutManager(activity, 2)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)
        basketData = ArrayList<ShopBestData>()

        //뒤로가기
        v.basket_back_btn.setOnClickListener{
            fragmentManager.popBackStack()
        }

        getBasketList()
        return v
    }

    fun getBasketList() {
        var getContentList = networkService!!.getBasketList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)

        getContentList.enqueue(object : Callback<ShopBestResponse> {
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful get my goods list")) {

                        basketData = response.body().result
                        CommonData.shopBestList = basketData!!
                        adapter = ShopAdapter(basketData, requestManager,CommonData.CALL_AT_TAB_TO_SHOP)
                        adapter!!.setOnItemClickListener(this@ChildMypageBasket)
                        basketList!!.adapter = adapter
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<ShopBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    override fun onClick(v: View?) {
        var intent = Intent(activity, ShopDetailActivity::class.java)
        intent.putExtra("Goods_Id", basketData!!.get(basketList!!.getChildAdapterPosition(v)).goods_id)
        intent.putExtra("bestFlag", CommonData.CALL_AT_TAB_TO_SHOP)
        startActivity(intent)
    }

}