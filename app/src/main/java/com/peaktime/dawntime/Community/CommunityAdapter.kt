package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by HYEON on 2017-12-31.
 */
class CommunityAdapter(var dataList : ArrayList<CommunityData>?) : RecyclerView.Adapter<CommunityViewholder>() {
    private var getItemClick : View.OnClickListener? = null
//    멤버변수 private 이 클래스 내에서만 사용

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommunityViewholder {
        //    어떤 뷰 홀더를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.communityitems, parent, false)
        mainView.setOnClickListener(getItemClick)
        return CommunityViewholder(mainView)
    }
    //뷰의내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: CommunityViewholder?, position: Int) {
        holder!!.communityImage.setImageResource(dataList!!.get(position).communityImage)
        holder!!.horesHead.setText(dataList!!.get(position).horseHead)
        holder!!.title.setText(dataList!!.get(position).title)
        holder!!.contents.setText(dataList!!.get(position).contents)
    }

    override fun getItemCount(): Int = dataList!!.size
//    몇개인지 리턴 별다른 함수 없이 리턴 있을때랑 똑같은 역할 (즉, return dataList!!.size)

    fun setOnItenClickListener(l: View.OnClickListener) {
        getItemClick = l
    }
}