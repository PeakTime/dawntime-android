package com.peaktime.dawntime

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.peaktime.dawntime.Community.CommunityFragment
import com.peaktime.dawntime.Home.HomeFragment
import com.peaktime.dawntime.MyPage.MyPageFragment

/**
 * Created by minhyoung on 2017. 12. 31..
 */
class TabAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var tabCount : Int = 0
    var homeTab : HomeFragment? = null
    var communityTab : CommunityFragment? = null
    var shopTab : ShopFragment? = null
    var myPageTab : MyPageFragment? = null

    constructor(fm : FragmentManager?,tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.homeTab = HomeFragment()
        this.communityTab = CommunityFragment()
        this.shopTab = ShopFragment()
        this. myPageTab = MyPageFragment()
    }

    override fun getCount(): Int = tabCount

    override fun getItem(position: Int): Fragment? {
        when(position){
            0 ->return homeTab
            1 ->return communityTab
            2 ->return shopTab
            3 ->return myPageTab
        }
        return null
    }
}