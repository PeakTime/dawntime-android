package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.recent_keword_item.view.*

/**
 * Created by xlsdn on 2018-01-08.
 */
class ShopSearchRecentKeywordAdapter(var dataList : ArrayList<ShopSearchRecentKeywordData>?) : RecyclerView.Adapter<ShopSearchRecentKeywordViewHolder>() {

    private var mainView : View?=null

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopSearchRecentKeywordViewHolder {
         mainView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.recent_keword_item, parent, false)

        mainView!!.setOnClickListener(onItemClick)

        return ShopSearchRecentKeywordViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopSearchRecentKeywordViewHolder?, position: Int) {
        holder!!.recentKeywordName.setText(dataList!!.get(position).recentKeywordName)

        mainView!!.delete_btn.setOnClickListener{
            deleteItem(holder, position)
//            mainView!!.kind_name_textview.setText("바뀜")
        }



    }


    fun deleteItem(holder: ShopSearchRecentKeywordViewHolder?, position: Int){

        dataList!!.removeAt(position)

        notifyItemRemoved(position)

        Toast.makeText(mainView!!.context, "삭제되었습니다", Toast.LENGTH_SHORT).show()

    }


    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size






}