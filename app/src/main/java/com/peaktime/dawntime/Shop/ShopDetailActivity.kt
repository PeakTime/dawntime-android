package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_detail.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log


class ShopDetailActivity : AppCompatActivity() , View.OnClickListener{
    var vp: ViewPager? = null
    var tabLayout: TabLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        shopBackBtn!!.setOnClickListener(this)
        shopSearchBtn!!.setOnClickListener(this)

        vp = findViewById(R.id.photos_viewpager)
        tabLayout = findViewById(R.id.tab_layout)

        var position: Int = intent.getIntExtra("position",0)

        if(ShopToMainActivity.bestFlagFun.bestFlag == 1) {

            if (position == 0 || position == 1 || position == 2) {

                goods_besttag.setBackgroundResource(R.drawable.shop_view_best_icon)

            }
        }


        //        var adapter = PhotosAdapter(getChildFragmentManager(), photosUrl)
        //adapter = CustomPagerAdapter(this)

        vp!!.setAdapter(ShopViewPagerAdapter(supportFragmentManager))
        vp!!.setCurrentItem(0)

        tabLayout!!.setupWithViewPager(vp!!, true)



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
