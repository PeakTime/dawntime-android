package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R

/**
 * Created by HYEON on 2017-12-31.
 */
class CommunityAdapter(var dataList: ArrayList<CommunityList>?, var requestManager: RequestManager) : RecyclerView.Adapter<CommunityViewholder>() {
    private var getItemClick : View.OnClickListener? = null
//    멤버변수 private 이 클래스 내에서만 사용

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommunityViewholder {
        //    어떤 뷰 홀더를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.community_data, parent, false)
        mainView.setOnClickListener(getItemClick)
        return CommunityViewholder(mainView)
    }
    //뷰의내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: CommunityViewholder?, position: Int) {

        requestManager.load(dataList!!.get(position).board_image).into(holder!!.communityImage)
        holder.horesHead.text = dataList!!.get(position).board_tag
        holder.title.text = dataList!!.get(position).board_title
        holder.contents.text = dataList!!.get(position).board_content
        holder.unfireContents.text = dataList!!.get(position).board_like.toString()
        holder.unreplyContents.text = dataList!!.get(position).com_count.toString()
        holder.unstarContents.text = dataList!!.get(position).scrap_count.toString()

        if (dataList!!.get(position).user_like == true)
            holder.fire_image.setImageResource(R.drawable.view_fire_red)
        else
            holder.fire_image.setImageResource(R.drawable.view_unfire_navy)

        if (dataList!!.get(position).user_scrap == true)
            holder.scrap_image.setImageResource(R.drawable.view_scrap_yellow)
        else
            holder.scrap_image.setImageResource(R.drawable.view_unscrap_navy)

    }

    override fun getItemCount(): Int = dataList!!.size
//    몇개인지 리턴 별다른 함수 없이 리턴 있을때랑 똑같은 역할 (즉, return dataList!!.size)

    fun setOnItemClickListener(l: View.OnClickListener) {
        getItemClick = l
    }
}