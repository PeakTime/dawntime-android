package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2017-12-31.
 */
class ShopAdapter(var dataList : ArrayList<ShopData>?) : RecyclerView.Adapter<ShopViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.shop_data, parent, false)

        mainView.setOnClickListener(onItemClick)

        return ShopViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        holder!!.shopImage.setImageResource(dataList!!.get(position).shopImage)
        holder!!.shopName.setText(dataList!!.get(position).shopName)
        holder!!.shopPrice.setText(dataList!!.get(position).shopPrice)
    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size
}