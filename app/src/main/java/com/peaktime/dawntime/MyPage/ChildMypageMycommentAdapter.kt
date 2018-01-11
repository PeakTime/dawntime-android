package com.peaktime.dawntime.MyPage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Community.CommunityList
import com.peaktime.dawntime.Community.CommunityViewholder
import com.peaktime.dawntime.R

/**
 * Created by 예은 on 2018-01-12.
 */
class ChildMypageMycommentAdapter(var dataList: ArrayList<MypageMycommentData>?, var requestManager: RequestManager) : RecyclerView.Adapter<MypageMycommentViewHolder>() {
    private var getItemClick : View.OnClickListener? = null
//    멤버변수 private 이 클래스 내에서만 사용

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MypageMycommentViewHolder {
        //    어떤 뷰 홀더를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.community_commentdata, parent, false)
        mainView.setOnClickListener(getItemClick)
        return MypageMycommentViewHolder(mainView)
    }
    //뷰의내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: MypageMycommentViewHolder?, position: Int) {

        requestManager.load(dataList!!.get(position).board_image).into(holder!!.communityImage)
        //이미지 리케스트로 가져오기
        holder.horesHead.text = dataList!!.get(position).board_tag
        holder.contents.text = dataList!!.get(position).com_content

    }

    override fun getItemCount(): Int = dataList!!.size
//    몇개인지 리턴 별다른 함수 없이 리턴 있을때랑 똑같은 역할 (즉, return dataList!!.size)

    fun setOnItemClickListener(l: View.OnClickListener) {
        getItemClick = l
    }
}