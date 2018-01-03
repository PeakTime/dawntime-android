package com.peaktime.dawntime.Shop.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.*
import kotlinx.android.synthetic.main.activity_shop_category.*
import kotlinx.android.synthetic.main.activity_shop_category.view.*

/**
 * Created by xlsdn on 2018-01-03.
 */
class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_category, container, false)

        var button : Button = v.findViewById(R.id.btn)
        button!!.setOnClickListener(this)

        //var backButton : ImageButton = v.findViewById(R.id.shopBackBtn)
        //v.backButton!!.setOnClickListener(this)

        //v.shopSearchBtn!!.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.btn -> {
                var intent = Intent(activity , ShopCategoryActivity::class.java)
                startActivity(intent)
            }

            R.id.shopBackBtn -> {
//                var transaction = fragmentManager.beginTransaction()
//                transaction.remove(this)
//                transaction.commit()
            }

            R.id.shopSearchBtn -> {
                var intent = Intent(activity, ShopSearchActivity::class.java)
                startActivity(intent)
            }


        }

    }




}