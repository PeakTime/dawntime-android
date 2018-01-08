package com.peaktime.dawntime.Shop

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.peaktime.dawntime.Shop.fragment.BrandFragment

/**
 * Created by minhyoung on 2018. 1. 7..
 */
class ShopBrandTabAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var tabCount : Int = 0
    var brandTab : BrandFragment? = null

    constructor(fm : FragmentManager?,tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.brandTab = BrandFragment()
    }

    override fun getItem(position: Int): Fragment? {
        return brandTab
    }

    override fun getCount(): Int = tabCount
}