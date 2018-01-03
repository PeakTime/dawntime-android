package com.peaktime.dawntime.Shop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_category.*

class ShopCategoryActivity : AppCompatActivity() , View.OnClickListener {

    //private  var shopCategoryList : RecyclerView?=null
    private  var shopCategoryDatas : ArrayList<ShopCategoryData>? = null
    private  var shopCategoryAdapter : ShopCategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_category)

//        shopCategoryList = findViewById(R.id.shop_category_list) as RecyclerView
        shop_category_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        shopCategoryDatas = ArrayList<ShopCategoryData>()
        shopCategoryDatas!!.add(ShopCategoryData("바이브레이터"))
        shopCategoryDatas!!.add(ShopCategoryData("커플토이"))
        shopCategoryDatas!!.add(ShopCategoryData("하네스 벨트"))
        shopCategoryDatas!!.add(ShopCategoryData("딜도"))
        shopCategoryDatas!!.add(ShopCategoryData("애널 케겔"))
        shopCategoryDatas!!.add(ShopCategoryData("PARTY 용품"))
        shopCategoryDatas!!.add(ShopCategoryData("코스튬"))
        shopCategoryDatas!!.add(ShopCategoryData("BDSM"))
        shopCategoryDatas!!.add(ShopCategoryData("속옷"))
        shopCategoryDatas!!.add(ShopCategoryData("콘돔"))
        shopCategoryDatas!!.add(ShopCategoryData("러브젤"))


        shopCategoryAdapter = ShopCategoryAdapter(shopCategoryDatas)
        shopCategoryAdapter!!.setOnItemClickListener(this)

        shop_category_list!!.adapter = shopCategoryAdapter


    }


    override fun onClick(v : View?) {


    }

}
