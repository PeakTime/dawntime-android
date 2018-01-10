package com.peaktime.dawntime.Shop

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.activity_shop_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ShopDetailActivity : AppCompatActivity() , View.OnClickListener{
    var vp: ViewPager? = null
    var tabLayout: TabLayout?=null

    var index :Int? = null
//내가한거
    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var shopDetailList : ShopDetailData ?= null

    var shopDetailName : TextView? =null
    var shopDetailPrice :TextView? = null
    var shopDetailBrand : TextView? =null
    var shopDetailInfo :TextView? = null

    var shopPagerAdapter : ShopViewPagerAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)
//내가한구
        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        shopBackBtn!!.setOnClickListener(this)
        shopSearchBtn!!.setOnClickListener(this)

        vp = findViewById(R.id.photos_viewpager)
        tabLayout = findViewById(R.id.tab_layout)

        shopDetailName = findViewById(R.id.shopDetailNameTextView)
        shopDetailPrice = findViewById(R.id.shopDetailPriceTextView)
        shopDetailBrand = findViewById(R.id.shopDetailBrandTextView)
        shopDetailInfo = findViewById(R.id.shopDetailContentTextView)

        var position: Int = intent.getIntExtra("position",0)

        if(ShopToMainActivity.bestFlagFun.bestFlag == 1) {

            if (position == 0 || position == 1 || position == 2) {
                goods_besttag.setBackgroundResource(R.drawable.shop_view_best_icon)
            }
        }

        index = intent.getIntExtra("Goods_Id",0)

        getShopDetailList()

        //var adapter = PhotosAdapter(getChildFragmentManager(), photosUrl)
        //adapter = CustomPagerAdapter(this)
        shopPagerAdapter = ShopViewPagerAdapter(supportFragmentManager)
        vp!!.setAdapter(shopPagerAdapter)
        vp!!.setCurrentItem(0)

        tabLayout!!.setupWithViewPager(vp!!, true)

    }


    fun getShopDetailList(){
        var getContentList = networkService!!.getShopDetailList(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!,index!!)

        getContentList.enqueue(object : Callback<ShopDetailResponse> {
            override fun onResponse(call: Call<ShopDetailResponse>?, response: Response<ShopDetailResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful get goods detail")) {

                        Log.i("status", "success")

                        shopDetailList = response.body().result

                        shopDetailName!!.setText(shopDetailList!!.goods_name)
                        shopDetailPrice!!.setText(shopDetailList!!.goods_price.toString())
                        shopDetailBrand!!.setText(shopDetailList!!.goods_brand)
                        shopDetailInfo!!.setText(shopDetailList!!.goods_info)
                    }
                } else {
                    Log.i("status", "fail")
                }
            }
            override fun onFailure(call: Call<ShopDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })
    }

    override fun onClick(v : View?) {


        when (v!!.id) {

            R.id.shopBackBtn -> {
                this.finish()
            }

            R.id.shopSearchBtn -> {
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }

        }
    }





}

