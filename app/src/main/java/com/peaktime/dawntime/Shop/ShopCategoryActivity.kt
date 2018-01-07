package com.peaktime.dawntime.Shop

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import kotlinx.android.synthetic.main.activity_shop_category.*
import kotlinx.android.synthetic.main.shop_kind_item.*

class ShopCategoryActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    private  var shopCategoryDatas : ArrayList<ShopKindData>? = null
    private  var shopCategoryAdapter : ShopKindAdapter? = null
    private var typeface : Typeface?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_category)



        shopBackBtn!!.setOnClickListener(clickListener)
        shopSearchBtn!!.setOnClickListener(clickListener)
        var categoryKindNum: Int = intent.getIntExtra("categoryKindNum",0)


        if(savedInstanceState == null){

            Toast.makeText(this, categoryKindNum.toString(), Toast.LENGTH_LONG).show()
            AddFragment(GoodsFragment(), categoryKindNum) //받은값으로 태그설정

        }

        shop_category_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        shopCategoryDatas = ArrayList<ShopKindData>()
        shopCategoryDatas!!.add(ShopKindData("바이브레이터"))
        shopCategoryDatas!!.add(ShopKindData("커플토이"))
        shopCategoryDatas!!.add(ShopKindData("하네스 벨트"))
        shopCategoryDatas!!.add(ShopKindData("딜도"))
        shopCategoryDatas!!.add(ShopKindData("애널 케겔"))
        shopCategoryDatas!!.add(ShopKindData("PARTY 용품"))
        shopCategoryDatas!!.add(ShopKindData("코스튬"))
        shopCategoryDatas!!.add(ShopKindData("BDSM"))
        shopCategoryDatas!!.add(ShopKindData("속옷"))
        shopCategoryDatas!!.add(ShopKindData("콘돔"))
        shopCategoryDatas!!.add(ShopKindData("러브젤"))


        shopCategoryAdapter = ShopKindAdapter(shopCategoryDatas)
        shopCategoryAdapter!!.setOnItemClickListener(this)

        shop_category_list!!.adapter = shopCategoryAdapter


        shop_sort_layout!!.setOnClickListener {
            val popup = PopupMenu(this, shop_sort_layout)//v는 클릭된 뷰를 의미
            this.menuInflater.inflate(R.menu.shop_sort_menu, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

    }

    override fun onClick(v : View?) {



        //리싸이클러뷰 이벤트
        val idx : Int = shop_category_list!!.getChildAdapterPosition(v) //position 받아오기
        val categoryName : String = shopCategoryDatas!!.get(idx).kindName //포지션에 위치하는 정보받아오기
        //Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show() //이름으로 토스트
        //Toast.makeText(this, idx.toString(), Toast.LENGTH_LONG).show()     //스트링으로 토스트

        when(idx){
            0->{
                val bundle = Bundle()
                //bundle.putString("categoryName", categoryName)
                //bundle.putString("title",firstText.text.toString())
                //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                ReplaceFragment(GoodsFragment(),0)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()
//                typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
//                kind_name_textview.setTypeface(typeface)
            }

            1->{
                //기능구현하면 GoodsFragment로 바꾸기
                ReplaceFragment(FirstFragment(),1)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

//                typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
//                kind_name_textview.setTypeface(typeface)


            }

            2->{
                //기능구현하면 GoodsFragment로 바꾸기
                ReplaceFragment(GoodsFragment(),2)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

//                typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
//                kind_name_textview.setTypeface(typeface)


            }

            3->{
                ReplaceFragment(GoodsFragment(),3)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

//                typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
//                kind_name_textview.setTypeface(typeface)
            }

            4->{
                ReplaceFragment(GoodsFragment(),4)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

//                typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
//                kind_name_textview.setTypeface(typeface)
            }
            5->{
                ReplaceFragment(GoodsFragment(),5)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            6->{
                ReplaceFragment(GoodsFragment(),6)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            7->{
                ReplaceFragment(GoodsFragment(),7)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            8->{
                ReplaceFragment(GoodsFragment(),8)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            9->{
                ReplaceFragment(GoodsFragment(),9)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            10->{
                ReplaceFragment(GoodsFragment(),10)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }
            11->{
                ReplaceFragment(GoodsFragment(),11)
                Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

            }

        }




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
