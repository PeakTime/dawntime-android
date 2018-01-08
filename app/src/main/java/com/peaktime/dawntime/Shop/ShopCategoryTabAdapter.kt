package com.peaktime.dawntime.Shop

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.peaktime.dawntime.Community.CommunityFragment
import com.peaktime.dawntime.Home.HomeFragment
import com.peaktime.dawntime.Shop.fragment.CategoryFragment
import com.peaktime.dawntime.ShopFragment

/**
 * Created by minhyoung on 2018. 1. 7..
 */
class ShopCategoryTabAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var tabCount : Int = 0
    var categoryTab : CategoryFragment? = null

    constructor(fm : FragmentManager?,tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.categoryTab = CategoryFragment()
    }

    override fun getItem(position: Int): Fragment? {
        return categoryTab
    }

    override fun getCount(): Int = tabCount

}