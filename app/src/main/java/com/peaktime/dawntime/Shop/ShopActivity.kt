package com.peaktime.dawntime.Shop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.peaktime.dawntime.R

class ShopActivity : AppCompatActivity() , View.OnClickListener{

    private  var shopList : RecyclerView?=null
    private  var shopDatas : ArrayList<ShopData>? = null
    private  var shopAdapter : ShopAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        shopList = findViewById(R.id.shop_list)
        shopList!!.layoutManager = GridLayoutManager(this, 2)

        shopDatas = ArrayList<ShopData>()
        shopDatas!!.add(ShopData(R.drawable.pika, "피카츄", "10000원"))
        shopDatas!!.add(ShopData(R.drawable.fire, "누구지", "15000원"))
        shopDatas!!.add(ShopData(R.drawable.pika, "피카츄", "10000원"))
        shopDatas!!.add(ShopData(R.drawable.pika, "피카츄", "10000원"))
        shopDatas!!.add(ShopData(R.drawable.fire, "누구지", "15000원"))
        shopDatas!!.add(ShopData(R.drawable.pika, "피카츄", "10000원"))



        shopAdapter = ShopAdapter(shopDatas)
        shopAdapter!!.setOnItemClickListener(this)

        shopList!!.adapter = shopAdapter
    }


    override fun onClick(v : View?) {
        val idx : Int = shopList!!.getChildAdapterPosition(v) //position 받아오기
        val name : String = shopDatas!!.get(idx).shopName //포지션에 위치하는 이름받아오기
        val price : String = shopDatas!!.get(idx).shopPrice

        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
    }
}
