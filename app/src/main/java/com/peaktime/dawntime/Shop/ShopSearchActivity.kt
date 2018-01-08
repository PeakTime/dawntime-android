package com.peaktime.dawntime.Shop

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.R.id.container
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.peaktime.dawntime.Community.CommunityWriteFragment
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*

class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        var search_content : String ?=null

        shopExitBtn!!.setOnClickListener(this)

        shopSearchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {

                    }
                    else ->
                    {
                        search_content = shopSearchEditText.text.toString()
                        val intent = Intent(this@ShopSearchActivity,ShopSearchResultActivity::class.java)
                        startActivityForResult(intent,0)
                        return false
                    }// 기본 엔터키 동작


                }// 검색 동작
                return true
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


}
