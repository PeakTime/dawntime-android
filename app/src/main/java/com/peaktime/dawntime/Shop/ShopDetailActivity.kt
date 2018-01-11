package com.peaktime.dawntime.Shop

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
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

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var shopDetailList : ShopDetailData ?= null

    var shopDetailName : TextView? =null
    var shopDetailPrice :TextView? = null
    var shopDetailBrand : TextView? =null
    var shopDetailInfo :TextView? = null
    var shopDetailUrl : String? = null
    var shopLike : Int? = null

    var shopPagerAdapter : ShopViewPagerAdapter? =null
    var selectedView : View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.status_shop)
        }

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        shopBackBtn!!.setOnClickListener(this)
        shopSearchBtn!!.setOnClickListener(this)
        shopLikeDetailBtn!!.setOnClickListener(this)
        shopDetailPageBtn.setOnClickListener(this)

        vp = findViewById(R.id.photos_viewpager)
        tabLayout = findViewById(R.id.tab_layout)


        shopDetailName = findViewById(R.id.shopDetailNameTextView)
        shopDetailPrice = findViewById(R.id.shopDetailPriceTextView)
        shopDetailBrand = findViewById(R.id.shopDetailBrandTextView)
        shopDetailInfo = findViewById(R.id.shopDetailContentTextView)


        var position: Int = intent.getIntExtra("position",0)
        var bestFlag: Int = intent.getIntExtra("bestFlag",0)

        if(bestFlag == CommonData.CALL_AT_HOME_TO_SHOP) {
            if (position == 0 || position == 1 || position == 2) {
                goods_besttag.setBackgroundResource(R.drawable.shop_view_best_icon)
            }
        }

        index = intent.getIntExtra("Goods_Id",0)
  //      selectedView = intent.getSerializableExtra("select_view") as View

        getShopDetailList()

        //var adapter = PhotosAdapter(getChildFragmentManager(), photosUrl)
        //adapter = CustomPagerAdapter(this)
//        shopPagerAdapter = ShopViewPagerAdapter(supportFragmentManager)
//        vp!!.setAdapter(shopPagerAdapter)
//        vp!!.setCurrentItem(0)
//
//        tabLayout!!.setupWithViewPager(vp!!, true)



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
                        shopDetailUrl = shopDetailList!!.goods_url
                        Log.v("ygLog", shopDetailList!!.goods_images[0])

                        shopPagerAdapter = ShopViewPagerAdapter(supportFragmentManager, shopDetailList!!.goods_images)
                        vp!!.setAdapter(shopPagerAdapter)
                        vp!!.setCurrentItem(0)

                        tabLayout!!.setupWithViewPager(vp!!, true)

                        //shopDetailList!!.

                        shopLike = shopDetailList!!.goods_like

                        if(shopLike==1){
                            shopLikeDetailBtn.setBackgroundResource(R.drawable.shop_tab_heart_solid)

                        }else if(shopLike==0){
                            shopLikeDetailBtn.setBackgroundResource(R.drawable.shop_tab_heart_line)
                        }

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

    fun putShopLike(){
        var getContentList = networkService!!.putShopLike(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!,index!!)

        getContentList.enqueue(object : Callback<ShopLikeResponse> {
            override fun onResponse(call: Call<ShopLikeResponse>?, response: Response<ShopLikeResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful regist basket")) {
                        //좋아요했을때
                        // Log.i("status", "성공성공성공성공성공성공성공성공성공성공성공")

                        CommonData.shopLikeSend!!.setBackgroundResource(R.drawable.view_heart_solid)
                        shopLikeDetailBtn!!.setBackgroundResource(R.drawable.shop_tab_heart_solid)


                    }
                    else if(response.body().message.equals("successful delete basket"))
                    {
                        Log.i("qwe","ㅁ니아ㅓㅁ니ㅏ")
                        //좋아요 취소했을때
                        CommonData.shopLikeSend!!.setBackgroundResource(R.drawable.view_heart_line)

                        shopLikeDetailBtn!!.setBackgroundResource(R.drawable.shop_tab_heart_line)
                       // shopLikeDetailBtn.invalidate()

                    }
                } else {
                    Log.i("status", "failfailfailfailfailfailfailfailfailfailfail")
//                    ApplicationController.instance!!.makeToast("북마크 실패.")
                }
            }
            override fun onFailure(call: Call<ShopLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })

    }



    override fun onClick(v : View?) {

        when (v!!.id) {

            R.id.shopBackBtn -> {
                this.finish()
                /*FragmentTransaction ft = getFragmentManager()!!.beginTransaction()
                ft.detach(this).attach(this)commit()*/
            }

            R.id.shopSearchBtn -> {
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }

        //암시적 인텐트 (구매페이지 이동)
            R.id.shopDetailPageBtn -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(shopDetailUrl))
                startActivity(intent)
            }
            R.id.shopLikeDetailBtn -> {
                putShopLike()
                Log.i("누룸", "눌림눌림")

            }
        }
    }

}

