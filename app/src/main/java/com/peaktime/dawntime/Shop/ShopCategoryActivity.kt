package com.peaktime.dawntime.Shop

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.load.engine.Resource
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_shop_category.*
import kotlinx.android.synthetic.main.activity_shop_category.view.*
import kotlinx.android.synthetic.main.shop_kind_item.*

class ShopCategoryActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    private  var shopCategoryDatas : ArrayList<ShopKindData>? = null
    private  var shopCategoryAdapter : ShopKindAdapter? = null
    private var typeface : Typeface?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_category)

        shop_category_list.addTab(shop_category_list.newTab().setText("바이브레이터"))
        shop_category_list.addTab(shop_category_list.newTab().setText("커플토이"))
        shop_category_list.addTab(shop_category_list.newTab().setText("하네스 벨트"))
        shop_category_list.addTab(shop_category_list.newTab().setText("딜도"))
        shop_category_list.addTab(shop_category_list.newTab().setText("애널 케겔"))
        shop_category_list.addTab(shop_category_list.newTab().setText("PARTY 용품"))
        shop_category_list.addTab(shop_category_list.newTab().setText("코스튬"))
        shop_category_list.addTab(shop_category_list.newTab().setText("BDSM"))
        shop_category_list.addTab(shop_category_list.newTab().setText("속옷"))
        shop_category_list.addTab(shop_category_list.newTab().setText("콘돔"))
        shop_category_list.addTab(shop_category_list.newTab().setText("러브젤"))

        setCustomFont(shop_category_list)


        shop_category_list.setSelectedTabIndicatorHeight(0)

        shopBackBtn!!.setOnClickListener(clickListener)
        shopSearchBtn!!.setOnClickListener(clickListener)
        var categoryKindNum: Int = intent.getIntExtra("categoryKindNum",0)


        if(savedInstanceState == null){

            Toast.makeText(this, categoryKindNum.toString(), Toast.LENGTH_LONG).show()
            AddFragment(GoodsFragment(), categoryKindNum) //받은값으로 태그설정
//            var tab = shop_category_list.getTabAt(categoryKindNum)
//            tab!!.select()
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


        shop_sort_layout!!.setOnClickListener {
            val popup = PopupMenu(this, shop_sort_layout)//v는 클릭된 뷰를 의미
            this.menuInflater.inflate(R.menu.shop_sort_menu, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
        //눌러진 MenuItem의 Item Id를 얻어와 식별

            R.id.latest_sort ->{
                sort_textview!!.text="최신순"
                Toast.makeText(this, "최신순", Toast.LENGTH_SHORT).show()
            }
            R.id.famous_sort ->{
                sort_textview!!.text="인기순"
                Toast.makeText(this, "인기순", Toast.LENGTH_SHORT).show()
            }
            R.id.highprice_sort ->{
                sort_textview!!.text="높은가격순"
                Toast.makeText(this, "높은가격순", Toast.LENGTH_SHORT).show()
            }
            R.id.loprice_sort ->{
                sort_textview!!.text="낮은가격순"
                Toast.makeText(this, "낮은가격순", Toast.LENGTH_SHORT).show()
            }


        }
        return false
    }






    //번들없는 함수
    fun AddFragment(fragment: Fragment, tag : Int){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.shop_category_viewpager,fragment,tag.toString())
        transaction.commit()
    }



    //번들없는 함수
    fun ReplaceFragment(fragment: Fragment,tag : Int){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.shop_category_viewpager,fragment, tag.toString())
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
