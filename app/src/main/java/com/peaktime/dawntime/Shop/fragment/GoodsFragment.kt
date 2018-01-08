package com.peaktime.dawntime.Shop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.MyPage.ChildMyPageMessageBoxAdapter
import com.peaktime.dawntime.MyPage.ChildMyPageMessageBoxData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.*
import kotlinx.android.synthetic.main.activity_shop_detail.*
import kotlinx.android.synthetic.main.fragment_shop_goods.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by xlsdn on 2018-01-03.
 */
class GoodsFragment : Fragment() , View.OnClickListener{

    private  var shopList : RecyclerView?=null
    private  var shopDatas : ArrayList<ShopData>? = null
    private  var shopAdapter : ShopAdapter? = null

    var shopBestList : ArrayList<ShopBestData> ?= null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_goods, container, false)


        shopList = v.findViewById(R.id.shopList)
        shopList!!.layoutManager = GridLayoutManager(activity, 2)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

//        shopDatas = ArrayList<ShopData>()
//        shopDatas!!.add(ShopData(R.drawable.pika, "피카츄", "10000원"))
//        shopDatas!!.add(ShopData(R.drawable.fire, "파이리", "15000원"))
//        shopDatas!!.add(ShopData(R.drawable.brown, "brown", "10000원"))
//        shopDatas!!.add(ShopData(R.drawable.cony, "cony", "10000원"))
//        shopDatas!!.add(ShopData(R.drawable.selly, "selly", "15000원"))
//        shopDatas!!.add(ShopData(R.drawable.jake, "jake", "10000원"))
//
//        shopAdapter = ShopAdapter(shopDatas)
//        shopAdapter!!.setOnItemClickListener(this)
//
//        shopList!!.adapter = shopAdapter

        v.fab.setOnClickListener(clickListener)
        v.fab.attachToRecyclerView(shopList!!)

        getShopBest()

        return v
    }

    override fun onClick(v : View?) {

        var intent = Intent(activity, ShopDetailActivity::class.java)

        val idx : Int = shopList!!.getChildAdapterPosition(v) //position 받아오기
        val name : String = shopDatas!!.get(idx).shopName //포지션에 위치하는 이름받아오기
        //val price : String = shopDatas!!.get(idx).shopPrice

        intent.putExtra("name", name)
        intent.putExtra("position", idx)

        startActivity(intent)
    }

    fun getShopBest() {
        var getContentList = networkService!!.getShopBestList("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozMywidXNlcl9lbWFpbCI6ImFzZnNkZmFzZGZAbmF2ZXIuY29tIiwidXNlcl91aWQiOiJ1aWR1aWR1aWQiLCJpYXQiOjE1MTU0Mjc0MjQsImV4cCI6MTUxNTUxMzgyNH0.Oh125-ew-ehCP9DwX-Xa-5tf0PpduZYddoSQA2-aU8QB69OGTZITmSc_YrqWCurwuBTHqppZmOCbGZaxKU_viA")

        getContentList.enqueue(object : Callback<ShopBestResponse> {
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful get best list")) {

                        Log.i("status", "success")
                        Log.i("size: ", response.body().result.toString())

                        shopBestList = response.body().result

                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_name)
                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_price.toString())

                        CommonData.shopBestList = shopBestList!!
                        shopAdapter = ShopAdapter(shopBestList,requestManager)
                        shopAdapter!!.setOnItemClickListener(this@GoodsFragment)
                        shopList!!.adapter = shopAdapter
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


    private var clickListener = View.OnClickListener { v ->
        //버튼이벤트
        when (v!!.id) {

            R.id.fab -> {
                //Toast.makeText(activity, "눌렸다", Toast.LENGTH_SHORT).show()
                activity.finish()
            }

        }
    } //버튼이벤트 end



}