package com.peaktime.dawntime.Shop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*

class ShopSearchActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)

        shopExitBtn!!.setOnClickListener(this)
    }



    override fun onClick(v : View?) {


        when (v!!.id) {

            R.id.shopExitBtn -> {
                this.finish()
            }

        }

    }


}
