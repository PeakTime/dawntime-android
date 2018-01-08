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





class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    private  var famousKeywordData : ArrayList<ShopSearchFamousKeywordData>? = null
    private  var famousAdapter : ShopSearchFamousKeywordAdapter? = null

    private  var recentKeywordData : ArrayList<ShopSearchRecentKeywordData>? = null
    private  var recentAdapter : ShopSearchRecentKeywordAdapter? = null

    var search_content : String ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        shopExitBtn!!.setOnClickListener(this)

        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.isAutoMeasureEnabled = true
        famous_keword_list.setLayoutManager(flowLayoutManager)

        famousKeywordData = ArrayList<ShopSearchFamousKeywordData>()
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
        recent_keword_list!!.adapter = recentAdapter

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
