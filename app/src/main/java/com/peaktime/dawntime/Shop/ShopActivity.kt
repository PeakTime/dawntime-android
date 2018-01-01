package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() , View.OnClickListener{

    private  var shopList : RecyclerView?=null
    private  var shopDatas : ArrayList<ShopData>? = null
    private  var shopAdapter : ShopAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        shopList = findViewById(R.id.shopList)
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


        shopSearchBtn!!.setOnClickListener(ClickListener)

    }

    override fun onClick(v : View?) {

        var intent = Intent(applicationContext, ShopDetailActivity::class.java)

        val idx : Int = shopList!!.getChildAdapterPosition(v) //position 받아오기
        val name : String = shopDatas!!.get(idx).shopName //포지션에 위치하는 이름받아오기
        //val price : String = shopDatas!!.get(idx).shopPrice

        intent.putExtra("name", name)
        startActivity(intent)
//        Toast.makeText(this, name, Toast.LENGTH_LONG).show()

    }


    private var ClickListener = View.OnClickListener { v ->
        when (v.id) {

            R.id.shopSearchBtn -> {
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                 startActivity(intent)
            }


        }
    }
}
