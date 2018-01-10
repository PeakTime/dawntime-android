package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R

/**
 * Created by xlsdn on 2018-01-08.
 */
class ShopSearchFamousKeywordAdapter(var dataList : ArrayList<String>?, var requestManagaer: RequestManager) : RecyclerView.Adapter<ShopSearchFamousKeywordViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopSearchFamousKeywordViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.famous_keyword_item, parent, false)

        mainView.setOnClickListener(onItemClick)

        return ShopSearchFamousKeywordViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopSearchFamousKeywordViewHolder?, position: Int) {
        holder!!.famousKeywordName.text = (dataList!!.get(position))

    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

//    override fun getItemCount(): Int{
//        return dataList!!.size
//    }




}