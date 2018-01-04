package com.peaktime.dawntime.Shop.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.*
import kotlinx.android.synthetic.main.fragment_shop_category.view.*

/**
 * Created by xlsdn on 2018-01-03.
 */
class CategoryFragment : Fragment(), View.OnClickListener {

//    private var onCategorySetListener: OnCategorySetListener? = null
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnCategorySetListener) {
//            onCategorySetListener = context as OnCategorySetListener
//        } else {
//            throw RuntimeException(context.toString() + "must implement OnCategorySetListener")
//        }
//
//    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_category, container, false)

        v.vibrator_btn!!.setOnClickListener(this)
        v.coupletoy_btn!!.setOnClickListener(this)
        v.belt_btn!!.setOnClickListener(this)

        v.dildo_btn!!.setOnClickListener(this)
        v.anal_btn!!.setOnClickListener(this)
        v.party_btn!!.setOnClickListener(this)

        v.costum_btn!!.setOnClickListener(this)
        v.BDSM_btn!!.setOnClickListener(this)
        v.underwear_btn!!.setOnClickListener(this)

        v.condom_btn!!.setOnClickListener(this)
        v.luvegel_btn!!.setOnClickListener(this)


        return v
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.vibrator_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",0)
                startActivity(intent)
            }
            R.id.coupletoy_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",1)
                startActivity(intent)
            }
            R.id.belt_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",2)
                startActivity(intent)
            }
            R.id.dildo_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",3)
                startActivity(intent)
            }
            R.id.anal_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",4)
                startActivity(intent)
            }
            R.id.party_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",5)
                startActivity(intent)
            }
            R.id.costum_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",6)
                startActivity(intent)
            }
            R.id.BDSM_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",7)
                startActivity(intent)
            }
            R.id.underwear_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",8)
                startActivity(intent)
            }
            R.id.condom_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",9)
                startActivity(intent)
            }
            R.id.luvegel_btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                intent.putExtra("categoryKindNum",10)
                startActivity(intent)
            }







        }

    }




//    interface OnCategorySetListener {
//        fun onCategorySet(num: Int)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        onCategorySetListener = null
//    }




}