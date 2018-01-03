package com.peaktime.dawntime.Shop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.ShopAdapter
import com.peaktime.dawntime.Shop.ShopData
import com.peaktime.dawntime.Shop.ShopDetailActivity

/**
 * Created by xlsdn on 2018-01-03.
 */
class NewFragment : Fragment() , View.OnClickListener{

    private  var shopList : RecyclerView?=null
    private  var shopDatas : ArrayList<ShopData>? = null
    private  var shopAdapter : ShopAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_new, container, false)


        shopList = v.findViewById(R.id.shopList)
        shopList!!.layoutManager = GridLayoutManager(activity, 2)

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


        return v
    }

    override fun onClick(v : View?) {

        var intent = Intent(activity, ShopDetailActivity::class.java)

        val idx : Int = shopList!!.getChildAdapterPosition(v) //position 받아오기
        val name : String = shopDatas!!.get(idx).shopName //포지션에 위치하는 이름받아오기
        //val price : String = shopDatas!!.get(idx).shopPrice

        intent.putExtra("name", name)
        startActivity(intent)
//        Toast.makeText(this, name, Toast.LENGTH_LONG).show()

    }

}