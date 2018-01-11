package com.peaktime.dawntime.MyPage

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import com.peaktime.dawntime.Shop.ShopBestData
import com.peaktime.dawntime.Shop.ShopLikeResponse
import com.peaktime.dawntime.Shop.ShopToMainActivity
import com.peaktime.dawntime.Shop.ShopViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 예은 on 2018-01-11.
 */
class ChildMypageBasketAdapter(var dataList : ArrayList<ShopBestData>?, var requestManager : RequestManager?) : RecyclerView.Adapter<ShopViewHolder>() {

    private var mainView : View?=null

    private var onItemClick : View.OnClickListener? = null

    var networkService  = ApplicationController.instance!!.networkService


    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.shop_goods_item, parent, false)

        mainView!!.setOnClickListener(onItemClick)

        return ShopViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {

        requestManager!!.load(dataList!!.get(position).goods_image).into(holder!!.shopImage)
        holder!!.shopName.setText(dataList!!.get(position).goods_name)
        holder!!.shopPrice.setText(dataList!!.get(position).goods_price.toString())

        if(dataList!!.get(position).goods_like == 1){
            holder!!.shopLikeBtn.setImageResource(R.drawable.view_heart_solid)
        }else{
            holder!!.shopLikeBtn.setImageResource(R.drawable.view_heart_line)
        }

//        holder!!.shopBrand.setText(dataList!!.get(position).goods_brand)
//        holder!!.shopInfo.setText(dataDetailList!!.get(position).goods_info)

    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

}