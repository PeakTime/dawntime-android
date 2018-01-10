package com.peaktime.dawntime.Shop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import com.peaktime.dawntime.Shop.*
import kotlinx.android.synthetic.main.fragment_shop_goods_sort.*
import kotlinx.android.synthetic.main.fragment_shop_goods_sort.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by xlsdn on 2018-01-10.
 */
class GoodsSortFragment : Fragment() , View.OnClickListener , PopupMenu.OnMenuItemClickListener{

    private  var shopList : RecyclerView?=null
    private var shopBestDatas : ArrayList<ShopBestData> ?= null
    private  var shopAdapter : ShopAdapter? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    var order : Int = 1
    var callAt : Int? = null
    var tabFlag : String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_goods_sort, container, false)
        callAt = arguments.getInt("callAt")

        shopList = v.findViewById(R.id.shopList)
        shopList!!.layoutManager = GridLayoutManager(activity, 2)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        v.fab.setOnClickListener(clickListener)
        v.fab.attachToRecyclerView(v.shopList!!)

        //정렬누르면 메뉴띄우기
        v.shop_sort_layout!!.setOnClickListener {
            val popup = PopupMenu(activity, v.shop_sort_layout)//v는 클릭된 뷰를 의미
            activity.menuInflater.inflate(R.menu.shop_sort_menu, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

        when(callAt){
            CommonData.CALL_AT_CATEGORY->{
                tabFlag = arguments.getString("categoryName")
                Log.i("tab bbb",tabFlag)
                getShopCategory(tabFlag!!,order!!)
            }
            CommonData.CALL_AT_BRAND->{
                tabFlag = arguments.getString("brandName")
                getShopBrand(tabFlag!!,order!!)
            }
            CommonData.CALL_AT_SEARCH->{

            }
        }



//        if(ShopToMainActivity.bestFlagFun.bestFlag == 1) {
//            Toast.makeText(activity, "bset들어옴", Toast.LENGTH_SHORT).show()
     //       getShopBest()
//
//        }

        return v
    }

    fun getShopCategory(categoryName : String,order : Int){
        val getContentList = networkService!!.getShopCategoryList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, categoryName, order)
        getContentList!!.enqueue(object : Callback<ShopBestResponse>{
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {
                if(response!!.body().message.equals("successful get category list")){

                    shopBestDatas = response.body().result
                    shopAdapter = ShopAdapter(shopBestDatas,requestManager)
                    shopAdapter!!.setOnItemClickListener(this@GoodsSortFragment)
                    shopList!!.adapter = shopAdapter
                }
            }

            override fun onFailure(call: Call<ShopBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })
    }

    fun getShopBrand(brandName : String,order : Int){
        val getContentList = networkService!!.getShopBrandList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!,brandName,order)
        getContentList!!.enqueue(object : Callback<ShopBestResponse>{
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {
                if(response!!.body().message.equals("successful get brand list")){

                    Log.d("들어옴","들어옴들어옴들어옴들어옴들어옴들어옴들어옴들어옴")

                    shopBestDatas = response.body().result
                    shopAdapter = ShopAdapter(shopBestDatas,requestManager)
                    shopAdapter!!.setOnItemClickListener(this@GoodsSortFragment)
                    shopList!!.adapter = shopAdapter
                }
            }

            override fun onFailure(call: Call<ShopBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })
    }

    override fun onClick(v : View?) {

        var intent = Intent(activity, ShopDetailActivity::class.java)
        intent.putExtra("Goods_Id",shopBestDatas!!.get(shopList!!.getChildAdapterPosition(v)).goods_id) //받는 데이터의 상품아이디
        startActivity(intent)
    }


    private var clickListener = View.OnClickListener { v ->
        //버튼이벤트
        when (v!!.id) {

            R.id.fab -> {
                //Toast.makeText(activity, "눌렸다", Toast.LENGTH_SHORT).show()
                activity.finish()
            }

        }
    } //버튼이벤트 end


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
        //눌러진 MenuItem의 Item Id를 얻어와 식별

            R.id.latest_sort ->{
                sort_textview!!.text="최신순"
                order = 1
                Toast.makeText(activity, "최신순", Toast.LENGTH_SHORT).show()
            }
            R.id.famous_sort ->{
                order = 2
                sort_textview!!.text="인기순"
                Toast.makeText(activity, "인기순", Toast.LENGTH_SHORT).show()
            }
            R.id.highprice_sort ->{
                order = 3
                sort_textview!!.text="높은가격순"
                Toast.makeText(activity, "높은가격순", Toast.LENGTH_SHORT).show()
            }
            R.id.loprice_sort ->{
                order = 4
                sort_textview!!.text="낮은가격순"
                Toast.makeText(activity, "낮은가격순", Toast.LENGTH_SHORT).show()
            }
        }

        when(callAt){
            CommonData.CALL_AT_CATEGORY->{
                tabFlag = arguments.getString("categoryName")
                getShopCategory(tabFlag!!,order!!)
            }
            CommonData.CALL_AT_BRAND->{
                tabFlag = arguments.getString("brandName")
                getShopBrand(tabFlag!!,order!!)
            }
            CommonData.CALL_AT_SEARCH->{

            }
        }

        return false
    }//end onMenuItemClick


}