package com.peaktime.dawntime.Shop

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.MenuItem
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.Resource
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import kotlinx.android.synthetic.main.activity_shop_category.*
import kotlinx.android.synthetic.main.activity_shop_category.view.*
import kotlinx.android.synthetic.main.shop_kind_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopCategoryActivity : AppCompatActivity(), View.OnClickListener{

    private  var shopCategoryDatas : ArrayList<ShopKindData>? = null
    private  var shopCategoryAdapter : ShopKindAdapter? = null
    private var typeface : Typeface?=null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_category)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        shop_category_list.addTab(shop_category_list.newTab().setText("바이브레이터"))
        shop_category_list.addTab(shop_category_list.newTab().setText("커플토이"))
        shop_category_list.addTab(shop_category_list.newTab().setText("딜도"))
        shop_category_list.addTab(shop_category_list.newTab().setText("애널 케겔"))
        shop_category_list.addTab(shop_category_list.newTab().setText("PARTY 용품"))
        shop_category_list.addTab(shop_category_list.newTab().setText("BDSM"))
        shop_category_list.addTab(shop_category_list.newTab().setText("러브젤"))
        shop_category_list.addTab(shop_category_list.newTab().setText("콘돔"))
        setCustomFont(shop_category_list)

        shop_category_list.setSelectedTabIndicatorHeight(0)

        shopBackBtn!!.setOnClickListener(clickListener)
        shopSearchBtn!!.setOnClickListener(clickListener)
        var categoryKindNum: Int = intent.getIntExtra("categoryKindNum",0)


        if(savedInstanceState == null){

            Toast.makeText(this, categoryKindNum.toString(), Toast.LENGTH_LONG).show()
            AddFragment(GoodsSortFragment(), shop_category_list.getTabAt(categoryKindNum)!!.text.toString()) //받은값으로 태그설정
            Handler().postDelayed(
                    Runnable { shop_category_list.getTabAt(categoryKindNum)!!.select() }, 100)

        }

        var tabAdapter = ShopCategoryTabAdapter(supportFragmentManager,shop_category_list.tabCount)

        shop_category_list.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                setCustomFont(shop_category_list)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                setCustomFont(shop_category_list,tab!!.position)
                ReplaceFragment(GoodsSortFragment(), tab!!.text.toString()) //받은값으로 태그설정
            }

        })

    }

    fun setCustomFont(tabLayout: TabLayout) {

        val vg = tabLayout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup

            val tabChildsCount = vgTab.childCount

            for (i in 0 until tabChildsCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    tabViewChild.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_light)
                }
            }
        }
    }

    fun setCustomFont(tabLayout: TabLayout,position : Int){
        val vg = tabLayout.getChildAt(0) as ViewGroup

        val vgTab = vg.getChildAt(position) as ViewGroup

        val tabChildsCount = vgTab.childCount

        for (i in 0 until tabChildsCount) {
            val tabViewChild = vgTab.getChildAt(i)
            if (tabViewChild is TextView) {
                //Put your font in assests folder
                //assign name of the font here (Must be case sensitive)
                tabViewChild.typeface = ResourcesCompat.getFont(baseContext,R.font.noto_sans_cjk_kr_medium)
            }
        }

    }

    override fun onClick(v : View?) {


    }//onClick end


    private var clickListener = View.OnClickListener { v ->
        //버튼이벤트
        when (v!!.id) {

            R.id.shopBackBtn -> {
                this.finish()
            }

            R.id.shopSearchBtn -> {
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }

        }
    } //버튼이벤트 end


    //번들없는 함수
    fun AddFragment(fragment: Fragment, shopCategoryTag: String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        var bundle = Bundle()
        bundle.putInt("callAt",CommonData.CALL_AT_CATEGORY)
        bundle.putString("categoryName",shopCategoryTag)
        fragment.arguments = bundle
        transaction.add(R.id.shop_category_viewpager,fragment)
        transaction.commit()
    }



    //번들없는 함수
    fun ReplaceFragment(fragment: Fragment,shopCategoryTag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        var bundle = Bundle()
        bundle.putInt("callAt",CommonData.CALL_AT_CATEGORY)
        bundle.putString("categoryName",shopCategoryTag)
        fragment.arguments = bundle
        transaction.replace(R.id.shop_category_viewpager,fragment)
        transaction.commit()
    }

//    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String){
//        val fm = supportFragmentManager
//        val transaction = fm.beginTransaction()
//        fragment.arguments = null
//        transaction.add(R.id.shop_category_viewpager,fragment,tag)
//        transaction.commit()
//    }
//
//
//    fun ReplaceFragment(fragment : Fragment, bundle : Bundle, tag : String){
//
//        val fm = supportFragmentManager
//        val transaction = fm.beginTransaction()
//        fragment.arguments = null
//        transaction.replace(R.id.shop_category_viewpager,fragment,tag)
//        transaction.commit()
//    }








}
