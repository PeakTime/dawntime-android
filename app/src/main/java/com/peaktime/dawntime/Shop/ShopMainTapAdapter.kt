package com.peaktime.dawntime.Shop

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.peaktime.dawntime.Shop.fragment.*

/**
 * Created by xlsdn on 2018-01-03.
 */
class ShopMainTapAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var tabCount : Int = 0
    var newTab : NewFragment? = null
    var categoryTab : CategoryFragment? = null
    var brandTab : BrandFragment? = null

    constructor(fm : FragmentManager?,tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.newTab = NewFragment()
        this.categoryTab = CategoryFragment()
        this.brandTab = BrandFragment()
    }

    override fun getCount(): Int = tabCount

    override fun getItem(position: Int): Fragment? {
        when(position){
            0 ->return newTab
            1 ->return categoryTab
            2 ->return brandTab
        }
        return null
    }
}