package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by xlsdn on 2017-12-31.
 */
class ShopAdapter(var dataList : ArrayList<ShopBestData>?, var requestManager : RequestManager?) : RecyclerView.Adapter<ShopViewHolder>() {

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
            holder!!.shopLikeBtn.setImageResource(R.drawable.shop_view_zzim_heart_solid)
        }else{
            holder!!.shopLikeBtn.setImageResource(R.drawable.shop_view_zzim_heart_line)
        }

//        holder!!.shopBrand.setText(dataList!!.get(position).goods_brand)
//        holder!!.shopInfo.setText(dataDetailList!!.get(position).goods_info)

        if(ShopToMainActivity.bestFlagFun.bestFlag == 1){

            if(position==0){
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no1)
//                mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no1)

            }
            if(position==1){
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no2)
                //mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no2)

            }
            if(position==2){
                holder!!.rankImage.setBackgroundResource(R.drawable.shop_view_best_no3)
                //mainView!!.goods_rangetag.setBackgroundResource(R.drawable.shop_view_best_no3)
            }
        }


//        holder!!.shopLikeBtn!!.setOnClickListener{
//            if(dataList!!.get(position).goods_like == 1){
//                putShopLike(position)
//            }
////                dataList!!.get(position).goods_like = 0
////                setLikeBtn(holder,position)
//            else{
//                putShopLike(position)
////                dataList!!.get(position).goods_like = 1
////                setLikeBtn(holder,position)
//            }

    }

        fun putShopLike(position: Int){
            var getContentList = networkService!!.putShopLike(SharedPreferInstance.getInstance(mainView!!.context).getPreferString("TOKEN")!!,position)

            getContentList.enqueue(object : Callback<ShopLikeResponse> {
                override fun onResponse(call: Call<ShopLikeResponse>?, response: Response<ShopLikeResponse>?) {
                    if (response!!.isSuccessful) {
                        if (response.body().message.equals("successful regist basket")) {
                            //좋아요했을때
                            Log.i("status", "성공성공성공성공성공성공성공성공성공성공성공")

//                            shopLikeDetailBtn!!.setBackgroundResource(R.drawable.shop_tab_heart_solid)


                        }
                        else if(response.body().message.equals("successful delete basket"))
                        {
                            Log.i("qwe","ㅁ니아ㅓㅁ니ㅏ)")
                            //좋아요 취소했을때

//                            shopLikeDetailBtn!!.setBackgroundResource(R.drawable.shop_tab_heart_line)
                            // shopLikeDetailBtn.invalidate()

                        }
                    } else {
                        Log.i("status", "failfailfailfailfailfailfailfailfailfailfail")
//                    ApplicationController.instance!!.makeToast("북마크 실패.")
                    }
                }
                override fun onFailure(call: Call<ShopLikeResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                    Log.i("status", "check")
                }

            })

        }

    fun setLikeBtn(holder: ShopViewHolder?, position: Int){

    }


    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

}