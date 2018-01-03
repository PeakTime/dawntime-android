package com.peaktime.dawntime.Home

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 1..
 */
class ColumnBannerAdapter(var dataList : ArrayList<ColumnBannerData>?) : PagerAdapter() {
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
        columnBannerImg.setImageResource(dataList!!.get(realPos).columnBannerImg)
        container.addView(v)

        return v
    }

}