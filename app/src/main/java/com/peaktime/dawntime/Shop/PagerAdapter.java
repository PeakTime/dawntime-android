package com.peaktime.dawntime.Shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.peaktime.dawntime.Shop.fragment.FirstFragment;
import com.peaktime.dawntime.Shop.fragment.FourthFragment;
import com.peaktime.dawntime.Shop.fragment.SecondFragment;
import com.peaktime.dawntime.Shop.fragment.ThirdFragment;
import com.peaktime.dawntime.Shop.fragment.fifthFragment;


/**
 * Created by xlsdn on 2018-01-02.
 */

public class PagerAdapter extends FragmentStatePagerAdapter
{
    public PagerAdapter(android.support.v4.app.FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            case 3:
                return new FourthFragment();
            case 4:
                return new fifthFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return 5;
    }
}
