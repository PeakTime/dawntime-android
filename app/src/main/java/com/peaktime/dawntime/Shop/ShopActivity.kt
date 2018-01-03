package com.peaktime.dawntime.Shop

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import com.peaktime.dawntime.Shop.ShopMainTapAdapter
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        shopBackBtn!!.setOnClickListener(clickListener)
        shopSearchBtn!!.setOnClickListener(clickListener)

        shop_tab.addTab(shop_tab.newTab().setText("NEW"))
        shop_tab.addTab(shop_tab.newTab().setText("CATEGORY"))
        shop_tab.addTab(shop_tab.newTab().setText("BRAND"))

        var tabAdapter = ShopMainTapAdapter(supportFragmentManager, shop_tab.tabCount)

        shop_main_viewpager.adapter = tabAdapter

//        shop_main_viewpager.currentItem = 0

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

        private var clickListener = View.OnClickListener { v ->
        when (v.id) {

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
