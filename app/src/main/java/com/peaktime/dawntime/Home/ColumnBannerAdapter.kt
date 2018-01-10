package com.peaktime.dawntime.Home

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 1..
 */
class ColumnBannerAdapter(var dataList : ArrayList<ColumnBannerData>?,var requestManager: RequestManager) : PagerAdapter() {

    var onItemClick : View.OnClickListener? = null
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = Int.MAX_VALUE

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var realPos : Int = position % dataList!!.size
        var v : View = LayoutInflater.from(container!!.context).inflate(R.layout.column_banner_items,container,false)
        var columnBannerImg : ImageView = v.findViewById(R.id.column_banner_img)
        requestManager.load(dataList!!.get(realPos).column_head).into(columnBannerImg)

        v!!.setOnClickListener(onItemClick)
        container.addView(v)

        return v
    }

    fun SetOnItemClickListener(l:View.OnClickListener) {
        onItemClick = l

    }

}