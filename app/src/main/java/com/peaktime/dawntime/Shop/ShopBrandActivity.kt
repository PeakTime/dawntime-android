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


        if(savedInstanceState == null){

            Toast.makeText(this, brandKindNum.toString(), Toast.LENGTH_LONG).show()
            AddFragment(GoodsFragment(), brandKindNum) //받은값으로 태그설정

        }

        shop_brand_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        shopBrandDatas = ArrayList<ShopKindData>()
        shopBrandDatas!!.add(ShopKindData("플레저 랩"))
        shopBrandDatas!!.add(ShopKindData("은하선 토이즈"))
        shopBrandDatas!!.add(ShopKindData("레드 컨테이너"))

        shopBrandAdapter = ShopKindAdapter(shopBrandDatas)
        shopBrandAdapter!!.setOnItemClickListener(this)

        shop_brand_list!!.adapter = shopBrandAdapter


    }

    override fun onClick(v : View?) {

        //리싸이클러뷰 이벤트
        val idx : Int = shop_brand_list!!.getChildAdapterPosition(v) //position 받아오기
        val brandName : String = shopBrandDatas!!.get(idx).kindName //포지션에 위치하는 정보받아오기


        when(idx){
            0->{
                val bundle = Bundle()
                //bundle.putString("BrandName", BrandName)
                //bundle.putString("title",firstText.text.toString())
                //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                ReplaceFragment(GoodsFragment(),0)
                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
            }

            1->{
                ReplaceFragment(GoodsFragment(),1)
                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
            }

            2->{
                ReplaceFragment(GoodsFragment(),2)
                Toast.makeText(this, brandName, Toast.LENGTH_LONG).show()
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



    //번들없는 함수
    fun AddFragment(fragment: Fragment, tag : Int){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.shop_brand_viewpager,fragment,tag.toString())
        transaction.commit()
    }



    //번들없는 함수
    fun ReplaceFragment(fragment: Fragment, tag : Int){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.shop_brand_viewpager,fragment, tag.toString())
        transaction.commit()
    }


}
