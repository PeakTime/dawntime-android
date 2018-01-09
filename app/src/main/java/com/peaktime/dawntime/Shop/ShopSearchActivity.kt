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
import com.peaktime.dawntime.Community.CommunityWriteFragment
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_community_detail_replyitem.view.*
import kotlinx.android.synthetic.main.fragment_community_detail_replyitem2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    private  var famousKeywordData : ArrayList<ShopSearchFamousKeywordData>? = null
    private  var famousAdapter : ShopSearchFamousKeywordAdapter? = null

    private  var recentKeywordData : ArrayList<ShopSearchRecentKeywordData>? = null
    private  var recentAdapter : ShopSearchRecentKeywordAdapter? = null

    var search_content : String ?=null

    var shopKeywordList : ArrayList<ShopKeywordData> ?= null
    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        shopExitBtn!!.setOnClickListener(this)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getShopKeyword()

        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.isAutoMeasureEnabled = true
        famous_keword_list.setLayoutManager(flowLayoutManager)

        /*famousKeywordData = ArrayList<ShopSearchFamousKeywordData>()
        famousKeywordData!!.add(ShopSearchFamousKeywordData("딜도"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("바이브레이터"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("커플용품"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("퀴어용품"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("야광콘돔"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("SM"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("마스터베이션"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("크리스마스"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("코스튬"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("핑크색"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("이벤트"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("인테리어"))
        famousKeywordData!!.add(ShopSearchFamousKeywordData("일코"))

        famousAdapter = ShopSearchFamousKeywordAdapter(famousKeywordData)
        famousAdapter!!.setOnItemClickListener(famousClickListener)
        famous_keword_list!!.adapter = famousAdapter

        val flowLayoutManager2 = FlowLayoutManager()
        flowLayoutManager2.isAutoMeasureEnabled = true
        recent_keword_list.setLayoutManager(flowLayoutManager2)

        recentKeywordData = ArrayList<ShopSearchRecentKeywordData>()
        recentKeywordData!!.add(ShopSearchRecentKeywordData("커플용품"))
        recentKeywordData!!.add(ShopSearchRecentKeywordData("남친선물"))
        recentKeywordData!!.add(ShopSearchRecentKeywordData("가터벨트"))

        recentAdapter = ShopSearchRecentKeywordAdapter(recentKeywordData)
        recentAdapter!!.setOnItemClickListener(recentClickListener)
        recent_keword_list!!.adapter = recentAdapter*/

        shopSearchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {

                    }
                    else ->
                    {
                        search_content = shopSearchEditText!!.text.toString()//검색키워드 받아오기
                        var intent = Intent(applicationContext, ShopSearchResultActivity::class.java)
                        intent.putExtra("keyword",search_content)
                        startActivity(intent)

                        return false
                    }// 기본 엔터키 동작

                }// 검색 동작
                return true
            }
        })

    }//onCreate

    fun getShopKeyword() {
        var getContentList = networkService!!.getShopKeyword("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozMywidXNlcl9lbWFpbCI6ImFzZnNkZmFzZGZAbmF2ZXIuY29tIiwidXNlcl91aWQiOiJ1aWR1aWR1aWQiLCJpYXQiOjE1MTU0Mjc0MjQsImV4cCI6MTUxNTUxMzgyNH0.Oh125-ew-ehCP9DwX-Xa-5tf0PpduZYddoSQA2-aU8QB69OGTZITmSc_YrqWCurwuBTHqppZmOCbGZaxKU_viA")

        getContentList.enqueue(object : Callback<ShopKeywordResponse> {
            override fun onResponse(call: Call<ShopKeywordResponse>?, response: Response<ShopKeywordResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful post keywords list")) {

                        Log.i("status", "success")
                        Log.i("size: ", response.body().result.toString())

                        shopKeywordList = response.body().result

//                        Log.i("sajldlkasjdkl",shopKeywordList!!.get(0).recent_keywords[0])
//                        Log.i("sajldlkasjdkl",shopKeywordList!!.get(0).hot_keywords[0])

                        CommonData.shopKeywordList = shopKeywordList!!
                        //최근 검색어
                        for (i in 0..shopKeywordList!!.get(0).recent_keywords!!.size - 1) {
                            recentAdapter = ShopSearchRecentKeywordAdapter(shopKeywordList)
                            recentAdapter!!.setOnItemClickListener(this@ShopSearchActivity)
                            recent_keword_list!!.adapter = recentAdapter
                        }
                        /*//인기 검색어
                        famousAdapter = ShopSearchFamousKeywordAdapter(shopKeywordList)
                        famousAdapter!!.setOnItemClickListener(this@ShopSearchActivity)
                        famous_keword_list!!.adapter = famousAdapter*/
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<ShopKeywordResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }


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
            R.id.shopExitBtn -> {
                this.finish()
            }

        }

    }

    private var famousClickListener = View.OnClickListener { v ->
        val famous_idx : Int = famous_keword_list!!.getChildAdapterPosition(v) //position 받아오기
        val famous_name : String = famousKeywordData!!.get(famous_idx).famousKeywordName //포지션에 위치하는 이름받아오기

        var intent = Intent(this, ShopSearchResultActivity::class.java)
        intent.putExtra("keyword", famous_name)
        startActivity(intent)

//        Toast.makeText(this, famous_name, Toast.LENGTH_SHORT).show()

        } //famousClickListener end


    private var recentClickListener = View.OnClickListener { v ->

        val recent_idx : Int = recent_keword_list!!.getChildAdapterPosition(v) //position 받아오기
        val recent_name : String = recentKeywordData!!.get(recent_idx).recentKeywordName //포지션에 위치하는 이름받아오기

        var intent = Intent(this, ShopSearchResultActivity::class.java)
        intent.putExtra("keyword", recent_name)
        startActivity(intent)


    } //recentClickListener end



}//final end
