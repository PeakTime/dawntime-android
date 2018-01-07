package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_to_main.*

class ShopToMainActivity : AppCompatActivity(), View.OnClickListener {

//    private val bestFlag : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_to_main)


        shopSearchBtn!!.setOnClickListener(this)

        shop_tab.addTab(shop_tab.newTab().setCustomView(R.layout.shop_customtab_best))


        var tabAdapter = ShopMainTapAdapter(supportFragmentManager, shop_tab.tabCount)

        shop_main_viewpager.adapter = tabAdapter

        shop_main_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(shop_tab))

        shop_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                shop_main_viewpager.currentItem = tab!!.position
            }
        })

    }


    override fun onClick(v : View?) {
        when (v!!.id) {

            R.id.shopSearchBtn -> {
//                Log.d("main","찍힘")
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }
        }
    }

    object bestFlagFun {

        var bestFlag : Int = 0

//        fun baz() {
//            // Do something
//        }
    }

}
