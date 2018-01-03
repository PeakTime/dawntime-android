package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-04.
 */
class ShopCategoryAdapter(var dataList : ArrayList<ShopCategoryData>?) : RecyclerView.Adapter<ShopCategoryViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopCategoryViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.shop_category_item, parent, false)

        mainView.setOnClickListener(onItemClick)

        return ShopCategoryViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopCategoryViewHolder?, position: Int) {
        holder!!.categoryName.setText(dataList!!.get(position).categoryName)
    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

//    override fun getItemCount(): Int{
//        return dataList!!.size
//    }




}