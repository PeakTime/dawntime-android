package com.peaktime.dawntime.Shop

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter

import com.peaktime.dawntime.Shop.fragment.FirstFragment
import com.peaktime.dawntime.Shop.fragment.FourthFragment
import com.peaktime.dawntime.Shop.fragment.SecondFragment
import com.peaktime.dawntime.Shop.fragment.ThirdFragment
import com.peaktime.dawntime.Shop.fragment.fifthFragment


/**
 * Created by xlsdn on 2018-01-02.
 */

class ShopViewPagerAdapter(fm: android.support.v4.app.FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            2 -> return ThirdFragment()
            3 -> return FourthFragment()
            4 -> return fifthFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 5
    }
}
