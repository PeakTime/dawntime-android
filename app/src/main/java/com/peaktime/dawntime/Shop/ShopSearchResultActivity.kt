package com.peaktime.dawntime.Shop

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopSearchResultActivity : AppCompatActivity() ,View.OnClickListener{

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var search : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search_result)


        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        //전의 액티비티에서 받은 키워드 확인
        var keyword : String = intent.getStringExtra("keyword")
        var lowPriceSt : String = intent.getStringExtra("lowPrice")
        var highPriceSt : String = intent.getStringExtra("highPrice")

//        if(keyword==""){
//            keyword="null"
//            Toast.makeText(applicationContext, "뭐가뜨냐"+keyword+"뭐가뜨냐", Toast.LENGTH_SHORT).show()
//        }


        //값넘길떄 쓸가격정보들 -> Int 로 보내야해서 형변환
        var lowPrice : Int = Integer.parseInt(lowPriceSt)
        var highPrice : Int = Integer.parseInt(highPriceSt)

        /*
        * 낮은가격만 들어오면 낮은가격 이상
        * 높은가격만 들어오면 높은가격 이하
        * 둘다 -1이면 검색어만
        * 둘다 들어오면 낮은가격 ~ 높은가격
        * */
        if(keyword != "") {

            if (lowPrice == -1 && highPrice == -1) {

                //검색어만 보이게
                search = "#" + keyword

            } else if (lowPrice == -1) {

                //검색어 보이거나 가격만 보이게
                search = highPriceSt + "원 이하 / #" + keyword

            } else if (highPrice == -1) {

                search = lowPriceSt + "원 이상 / #" + keyword

            } else {
                search = lowPriceSt + "원 ~ " + highPriceSt + "원 / #" + keyword
            }
        }else{
            if (lowPrice == -1 && highPrice == -1) {

                //검색어만 보이게
//                search = "#" + keyword
                Toast.makeText(applicationContext, "이게나오면잘못된로직", Toast.LENGTH_SHORT).show()

            } else if (lowPrice == -1) {

                //검색어 보이거나 가격만 보이게
                search = highPriceSt + "원 이하"

            } else if (highPrice == -1) {

                search = lowPriceSt + "원 이상"

            } else {
                search = lowPriceSt + "원 ~ " + highPriceSt +"원"
            }
        }

        shop_search_text.text = search

//        Toast.makeText(this, lowPriceSt, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, highPriceSt, Toast.LENGTH_LONG).show()

//        shopSearch(lowPrice, highPrice, keyword)

        //뒤로가기버튼
        shopBackBtn!!.setOnClickListener(this)

    }

    override fun onClick(v : View?) {

        when (v!!.id) {
            R.id.shopBackBtn -> {
                this.finish()
            }
        }
    }//end onClick


    fun shopSearch(lowPrice:Int, highPrice:Int, keyword : String){
        val shopSearchResponse = networkService!!.shopSearch("", 1, ShopSearchRequest(
                lowPrice, highPrice, keyword
        ))

        shopSearchResponse.enqueue(object : Callback<ShopSearchResponse> {

            override fun onResponse(call: Call<ShopSearchResponse>?, response: Response<ShopSearchResponse>?) {
                if(response!!.isSuccessful){

                    if(response!!.body().status.equals("success")){

                        ApplicationController.instance!!.makeToast("등록 완료.")


                    }else{
                        ApplicationController.instance!!.makeToast("등록 실패.")
                    }
                }
            }

            override fun onFailure(call: Call<ShopSearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }



}
