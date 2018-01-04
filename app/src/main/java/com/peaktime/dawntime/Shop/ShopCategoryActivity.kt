package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.*
import kotlinx.android.synthetic.main.activity_shop_category.*

class ShopCategoryActivity : AppCompatActivity(), View.OnClickListener{

    private  var shopCategoryDatas : ArrayList<ShopKindData>? = null
    private  var shopCategoryAdapter : ShopKindAdapter? = null
   // private var categoryKindNum: Int = intent.getIntExtra("categoryKindNum",0)
    //internal var categoryKindNum

//    override fun onCategorySet(num: Int) {
//        categoryKindNum = num
//    }

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
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()
            }

            1->{
                //기능구현하면 GoodsFragment로 바꾸기
                ReplaceFragment(GoodsFragment(),1)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()
            }

            2->{
                //기능구현하면 GoodsFragment로 바꾸기
                ReplaceFragment(GoodsFragment(),2)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()
            }

            3->{
                ReplaceFragment(GoodsFragment(),3)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()
            }

            4->{
                ReplaceFragment(GoodsFragment(),4)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()
            }
            5->{
                ReplaceFragment(GoodsFragment(),5)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            6->{
                ReplaceFragment(GoodsFragment(),6)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            7->{
                ReplaceFragment(GoodsFragment(),7)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            8->{
                ReplaceFragment(GoodsFragment(),8)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            9->{
                ReplaceFragment(GoodsFragment(),9)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            10->{
                ReplaceFragment(GoodsFragment(),10)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

            }
            11->{
                ReplaceFragment(GoodsFragment(),11)
                Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show()

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

        } //버튼이벤트 end
    }




    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = null
        transaction.add(R.id.shop_category_viewpager,fragment,tag)
        transaction.commit()
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


    fun ReplaceFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = null
        transaction.replace(R.id.shop_category_viewpager,fragment,tag)
        transaction.commit()
    }








}
