package com.peaktime.dawntime.Shop

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.PopupMenu
import android.widget.Toast
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import kotlinx.android.synthetic.main.activity_shop_brand.*

class ShopBrandActivity : AppCompatActivity() , View.OnClickListener{

    private  var shopBrandDatas : ArrayList<ShopKindData>? = null
    private  var shopBrandAdapter : ShopKindAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_brand)

        shopBackBtn!!.setOnClickListener(clickListener)
        shopSearchBtn!!.setOnClickListener(clickListener)
        var brandKindNum: Int = intent.getIntExtra("brandKindNum",0)

        shop_brand_list.addTab(shop_brand_list.newTab()!!.setText("플레저 랩"))
        shop_brand_list.addTab(shop_brand_list.newTab()!!.setText("은하선 토이즈"))
        shop_brand_list.addTab(shop_brand_list.newTab()!!.setText("바른생각"))
        shop_brand_list.addTab(shop_brand_list.newTab()!!.setText("식스티 원"))
        shop_brand_list.setSelectedTabIndicatorHeight(0)
        setCustomFont(shop_brand_list)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.status_shop)
        }


        if(savedInstanceState == null){

//            Toast.makeText(this, brandKindNum.toString(), Toast.LENGTH_LONG).show()
            //AddFragment(GoodsSortFragment(), shop_brand_list.getTabAt(brandKindNum)!!.text.toString()) //받은값으로 태그설정
            Handler().postDelayed(
                    Runnable { setCustomFont(shop_brand_list,brandKindNum )
                        shop_brand_list.getTabAt(brandKindNum)!!.select() }, 100)

        }

        var tabAdapter = ShopBrandTabAdapter(supportFragmentManager,shop_brand_list.tabCount)

        shop_brand_list.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                setCustomFont(shop_brand_list)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                setCustomFont(shop_brand_list,tab!!.position)
                ReplaceFragment(GoodsSortFragment(),tab!!.text.toString())
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

        //리싸이클러뷰 이벤트
//        val idx : Int = shop_brand_list!!.getChildAdapterPosition(v) //position 받아오기
//        val brandName : String = shopBrandDatas!!.get(idx).kindName //포지션에 위치하는 정보받아오기
//
//
//        when(idx){
//            0->{
//                val bundle = Bundle()
//                //bundle.putString("BrandName", BrandName)
//                //bundle.putString("title",firstText.text.toString())
//                //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
//                ReplaceFragment(GoodsFragment(),0)
//                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
//            }
//
//            1->{
//                ReplaceFragment(GoodsFragment(),1)
//                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
//            }
//
//            2->{
//                ReplaceFragment(GoodsFragment(),2)
//                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
//            }
//
//
//
//        }




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

        } //버튼이벤트 end
    }




    //번들없는 함수
    fun AddFragment(fragment: Fragment, shopBrandtag : String ){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        var bundle = Bundle()
        bundle.putInt("callAt", CommonData.CALL_AT_BRAND)
        bundle.putString("brandName",shopBrandtag)
        fragment.arguments = bundle
        transaction.add(R.id.shop_brand_viewpager,fragment)
        transaction.commit()
    }



    //번들없는 함수
    fun ReplaceFragment(fragment: Fragment, shopBrandtag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        var bundle = Bundle()
        bundle.putInt("callAt", CommonData.CALL_AT_BRAND)
        bundle.putString("brandName",shopBrandtag)
        fragment.arguments = bundle
        transaction.replace(R.id.shop_brand_viewpager,fragment)
        transaction.commit()
    }


}
