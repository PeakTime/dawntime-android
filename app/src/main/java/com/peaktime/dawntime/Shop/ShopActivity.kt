package com.peaktime.dawntime.Shop

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.shop_customtab_brand.*
import kotlinx.android.synthetic.main.shop_customtab_category.*
import kotlinx.android.synthetic.main.shop_customtab_new.*


class ShopActivity : AppCompatActivity() , View.OnClickListener{

//    internal var categoryKindNum = 0
//
//    override fun onCategorySet(num: Int) {
//        categoryKindNum = num
//        //Toast.makeText(this, categoryKindNum.toString(), Toast.LENGTH_LONG).show()
//    }


    override fun onStart() {
        super.onStart()
        Log.i("qwerty","onStart()")
    }

//    override fun onResume() {
////        val intent = Intent(this@ShopActivity, ShopActivity::class.java)
////        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
////        startActivity(intent)
//        super.onResume()
//    }

    override fun onRestart() {
        Log.i("qwerty","onRestart()")

        super.onRestart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        shopBackBtn!!.setOnClickListener(this)
        shopSearchBtn!!.setOnClickListener(this)

        shop_tab.addTab(shop_tab.newTab().setCustomView(R.layout.shop_customtab_new))
        shop_tab.addTab(shop_tab.newTab().setCustomView(R.layout.shop_customtab_category))
        shop_tab.addTab(shop_tab.newTab().setCustomView(R.layout.shop_customtab_brand))


        var tabAdapter = ShopMainTapAdapter(supportFragmentManager, shop_tab.tabCount)

        shop_main_viewpager.adapter = tabAdapter

        shop_main_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(shop_tab))

        shop_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                when(tab!!.position){
//                    1->{
//                        //tab!!.customView!!.brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                        brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                    }
//                    2->{
//                        //tab!!.customView!!.category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                        category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                    }
//                    3->{
//                        //tab!!.customView!!.new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                        new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
//                    }
//                }
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                shop_main_viewpager.currentItem = tab!!.position
                when(tab!!.position){
                    0->{
                        //tab!!.customView!!.brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)
                        new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)

                        category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                        brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)

                        var bundle = Bundle()
                        bundle.putInt("bestFlag", CommonData.CALL_AT_TAB_TO_SHOP)
                        GoodsFragment().arguments = bundle
//                        GoodsSortFragment().arguments = bundle

                    }
                    1->{
                        //tab!!.customView!!.category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)
                        category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)

                        brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                        new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                    }
                    2->{
                        //tab!!.customView!!.new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)
                        brand_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)

                        new_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                        category_tab_text.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                    }
                }
            }
        })

    }


    override fun onClick(v : View?) {
        when (v!!.id) {
            R.id.shopBackBtn -> {this.finish()}

            R.id.shopSearchBtn -> {
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
