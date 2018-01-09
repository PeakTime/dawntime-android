package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.shop_goods_item.view.*


/**
 * Created by xlsdn on 2017-12-31.
 */
class ShopAdapter(var dataList : ArrayList<ShopBestData>?,
                  var requestManager : RequestManager?) : RecyclerView.Adapter<ShopViewHolder>() {

    private var mainView : View?=null

    private var onItemClick : View.OnClickListener? = null

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
//        holder!!.shopBrand.setText(dataList!!.get(position).goods_brand)
//        holder!!.shopInfo.setText(dataDetailList!!.get(position).goods_info)


        if(ShopToMainActivity.bestFlagFun.bestFlag == 1){

            if(position==0){
                mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no1)

            }
            if(position==1){
                mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no2)

            }
            if(position==2){
                mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no3)
            }
        }
        if(dataList!!.get(position).goods_like == 1)
            mainView!!.shopLikeBtn.setBackgroundResource(R.drawable.shop_view_zzim_heart_solid)
        else
            mainView!!.shopLikeBtn.setBackgroundResource(R.drawable.shop_view_zzim_heart_line)

    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

}