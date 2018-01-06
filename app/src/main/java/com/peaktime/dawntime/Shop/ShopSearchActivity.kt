package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*

class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        shopExitBtn!!.setOnClickListener(this)

//        shopSearchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
//            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
//                when (actionId) {
//                    EditorInfo.IME_ACTION_SEARCH -> {
//                    }
//                    else ->
//                        // 기본 엔터키 동작
//                        return false
//                }// 검색 동작
//                return true
//            }
//        })

    }



    override fun onClick(v : View?) {


        when (v!!.id) {

            R.id.shopExitBtn -> {
                this.finish()
            }

        }

    }


}
