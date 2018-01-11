package com.peaktime.dawntime.Shop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.ShopBrandActivity
import kotlinx.android.synthetic.main.fragment_shop_brand.view.*

/**
 * Created by xlsdn on 2018-01-03.
 */
class BrandFragment : Fragment(), View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_brand, container, false)

        v.brand_first_btn!!.setOnClickListener(this)
        v.brand_second_btn!!.setOnClickListener(this)
        v.brand_third_btn!!.setOnClickListener(this)
        v.brand_forth_btn!!.setOnClickListener(this)

        return v
    }


    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.brand_first_btn -> {
                var intent = Intent(activity , ShopBrandActivity::class.java)
                intent.putExtra("brandKindNum",0)
                startActivity(intent)
            }
            R.id.brand_second_btn -> {
                var intent = Intent(activity , ShopBrandActivity::class.java)
                intent.putExtra("brandKindNum",1)
                startActivity(intent)
            }
            R.id.brand_third_btn -> {
                var intent = Intent(activity , ShopBrandActivity::class.java)
                intent.putExtra("brandKindNum",2)
                startActivity(intent)
            }
            R.id.brand_third_btn -> {
                var intent = Intent(activity , ShopBrandActivity::class.java)
                intent.putExtra("brandKindNum",3)
                startActivity(intent)
            }

        }

    }

}