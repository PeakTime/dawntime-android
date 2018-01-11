package com.peaktime.dawntime.Shop

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.peaktime.dawntime.Shop.fragment.*


/**
 * Created by xlsdn on 2018-01-02.
 */

class ShopViewPagerAdapter(fm: android.support.v4.app.FragmentManager, var images : ArrayList<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putString("image", images[0])
                val firstFragment = FirstFragment()
                firstFragment.arguments = bundle
                return firstFragment
            }
            1 -> {
                val bundle = Bundle()
                bundle.putString("image", images[1])
                val firstFragment = SecondFragment()
                firstFragment.arguments = bundle
                return firstFragment
            }
            2 -> {
                val bundle = Bundle()
                bundle.putString("image", images[2])
                val firstFragment = ThirdFragment()
                firstFragment.arguments = bundle
                return firstFragment
            }
            3 -> {
                val bundle = Bundle()
                bundle.putString("image", images[3])
                val firstFragment = FourthFragment()
                firstFragment.arguments = bundle
                return firstFragment
            }
            4 -> {
                val bundle = Bundle()
                bundle.putString("image", images[4])
                val firstFragment = fifthFragment()
                firstFragment.arguments = bundle
                return firstFragment
            }
            else -> return null
        }
    }


    override fun getCount(): Int {
        return images.size
    }
}
