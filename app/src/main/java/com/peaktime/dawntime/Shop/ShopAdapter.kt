package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by xlsdn on 2017-12-31.
 */
class ShopAdapter(var dataList : ArrayList<ShopBestData>?,
                  var requestManager : RequestManager?, var bestFlag : Int?) : RecyclerView.Adapter<ShopViewHolder>() {

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
        holder!!.shopPrice.setText(dataList!!.get(position).goods_price)

        if(bestFlag == CommonData.CALL_AT_HOME_TO_SHOP){

            if (position == 0) {
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no1)
//                mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no1)

            }
            if (position == 1) {
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no2)
                //mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no2)

            }
            if (position == 2) {
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no3)
                //mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no3)
            }
        }


        if (dataList!!.get(position).goods_like == 1) {
            holder!!.shopLikeBtn.setBackgroundResource(R.drawable.view_heart_solid)
        } else {
            holder!!.shopLikeBtn.setBackgroundResource(R.drawable.view_heart_line)
    }

//        holder!!.shopBrand.setText(dataList!!.get(position).goods_brand)
//        holder!!.shopInfo.setText(dataDetailList!!.get(position).goods_info)



        holder!!.shopLikeBtn!!.setOnClickListener {

            putShopLike(holder, dataList!!.get(position).goods_id)

        }
    }



    fun putShopLike(holder: ShopViewHolder?, goodsId: Int) {
        var getContentList = networkService!!.putShopLike(SharedPreferInstance.getInstance(mainView!!.context).getPreferString("TOKEN")!!, goodsId)

        getContentList.enqueue(object : Callback<ShopLikeResponse> {
            override fun onResponse(call: Call<ShopLikeResponse>?, response: Response<ShopLikeResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful regist basket")) {
                        //좋아요했을때

                        holder!!.shopLikeBtn.setBackgroundResource(R.drawable.view_heart_solid)

                    } else if (response.body().message.equals("successful delete basket")) {
                        //좋아요 취소했을때

                        holder!!.shopLikeBtn.setBackgroundResource(R.drawable.view_heart_line)
                        // shopLikeDetailBtn.invalidate()

                    }
                } else {
                    Log.i("status", "fail")
//                    ApplicationController.instance!!.makeToast("북마크 실패.")
                }
            }

            override fun onFailure(call: Call<ShopLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })

    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

}