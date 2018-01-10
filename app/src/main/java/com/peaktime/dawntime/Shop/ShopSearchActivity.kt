package com.peaktime.dawntime.Shop

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.R.id.container
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.SharedPreferInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    private  var famousKeywordData : ArrayList<String>? = null
    private  var recentKeywordData : ArrayList<String>? = null
    private  var famousAdapter : ShopSearchFamousKeywordAdapter? = null
    private  var recentAdapter : ShopSearchRecentKeywordAdapter? = null


    var search_content : String ?=null
    var lowPrice : String ?=null
    var highPrice : String ?=null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        resetBtn.setOnClickListener(this)

        //인기키워드 리사이클러뷰
        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.isAutoMeasureEnabled = true
        famous_keword_list.setLayoutManager(flowLayoutManager)

        //최근검색어 리사이클러뷰
        val flowLayoutManager2 = FlowLayoutManager()
        flowLayoutManager2.isAutoMeasureEnabled = true
        recent_keword_list.setLayoutManager(flowLayoutManager2)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getKeywordList()

        //enter누르면 검색창으로 이동
        shopSearchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {

                        shopSearchEditText.text = null


                    }
                    else ->
                    {

                        search_content = shopSearchEditText!!.text.toString()//검색키워드 받아오기
//                        highPrice = highPriceEditText!!.text.toString()

                        //낮은가격 값넘기기 + 예외처리
                        if(lowPriceEditText!!.text.toString()==""){
                            lowPrice="-1"
                        }else{
                            lowPrice = lowPriceEditText!!.text.toString()
                        }

                        //높은가격 값 넘기기 + 예외처리
                        if(highPriceEditText!!.text.toString()==""){
                            highPrice="-1"
                        }else{
                            highPrice = highPriceEditText!!.text.toString()
                        }

                        var lowPriceInt : Int = Integer.parseInt(lowPrice)
                        var highPriceInt : Int = Integer.parseInt(highPrice)


                        //입력된 값이 하나라도 없으면 예외처리
                        if(lowPrice.equals("-1")&&highPrice.equals("-1")&&search_content.equals("")){
                            Toast.makeText(applicationContext, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                        }else if(lowPriceInt!=-1&&highPriceInt!=-1){

                            if(lowPriceInt>highPriceInt) {
                                Toast.makeText(applicationContext, "최저가격 값이 최고가격 값보다 큽니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                            }

                        }else{


                            var intent = Intent(applicationContext, ShopSearchResultActivity::class.java)
                            intent.putExtra("keyword",search_content)
                            intent.putExtra("lowPrice", lowPrice)
                            intent.putExtra("highPrice", highPrice)

                            startActivity(intent)

                        }

                        shopSearchEditText.text = null



                        return false
                    }// 기본 엔터키 동작

                }// 검색 동작
                return true
            }
        })

        //X버튼누르면 액티비티 finish
        shopExitBtn!!.setOnClickListener(this)

    }//onCreate


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            var recentSearch : RelativeLayout ?= null
            recentSearch = findViewById(R.id.lineImage2)
            var search_keyword : TextView ?= null
            search_keyword
        }

    }

    override fun onClick(v : View?) {

        when (v!!.id) {
            R.id.resetBtn -> {
                lowPriceEditText.text=null
                highPriceEditText.text=null
            }

            R.id.shopExitBtn -> {
                this.finish()
            }

        }

    }

    private var famousClickListener = View.OnClickListener { v ->
        val famous_idx : Int = famous_keword_list!!.getChildAdapterPosition(v) //position 받아오기
        val famous_name : String = famousKeywordData!!.get(famous_idx) //포지션에 위치하는 이름받아오기

        var intent = Intent(this, ShopSearchResultActivity::class.java)
        intent.putExtra("keyword", famous_name)
        intent.putExtra("lowPrice", "-1")
        intent.putExtra("highPrice", "-1")

        startActivity(intent)

        } //famousClickListener end

    private var recentClickListener = View.OnClickListener { v ->

        val recent_idx : Int = recent_keword_list!!.getChildAdapterPosition(v) //position 받아오기
        val recent_name : String = recentKeywordData!!.get(recent_idx) //포지션에 위치하는 이름받아오기

        var intent = Intent(this, ShopSearchResultActivity::class.java)
        intent.putExtra("keyword", recent_name)
        intent.putExtra("lowPrice", "-1")
        intent.putExtra("highPrice", "-1")
        startActivity(intent)


    } //recentClickListener end


    fun getKeywordList(){
        var getKeywordList = networkService!!.getKeywordList(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!)
        getKeywordList.enqueue(object : Callback<ShopKeywordResponse> {
            override fun onResponse(call: Call<ShopKeywordResponse>?, response: Response<ShopKeywordResponse>?) {
                if (response!!.isSuccessful) {
                    //pokemonList!!.layoutManager = LinearLayoutManager(this)
                    if (response.body().message.equals("successful post keywords list")) {

                        famousKeywordData = response.body().result.hot_keywords
                        famousAdapter = ShopSearchFamousKeywordAdapter(famousKeywordData, requestManager!!)
                        famousAdapter!!.setOnItemClickListener(famousClickListener)
                        famous_keword_list!!.adapter = famousAdapter


                        recentKeywordData = response.body().result.recent_keywords
                        recentAdapter = ShopSearchRecentKeywordAdapter(recentKeywordData, requestManager!!)
                        recentAdapter!!.setOnItemClickListener(recentClickListener)
                        recent_keword_list!!.adapter = recentAdapter


                    }
                }else{
                    Log.d("fail","fail")

                }
            }
            override fun onFailure(call: Call<ShopKeywordResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.v("checkNetwork","checkNetwork")
            }
        })
    }


}//final end
