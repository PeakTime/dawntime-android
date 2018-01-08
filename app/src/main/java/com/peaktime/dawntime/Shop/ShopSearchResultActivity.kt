package com.peaktime.dawntime.Shop

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.activity_shop_search.*

class ShopSearchResultActivity : AppCompatActivity() ,View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search_result)

        var keyword : String = intent.getStringExtra("keyword")
        Toast.makeText(this, keyword, Toast.LENGTH_SHORT).show()

        var shopBackBtn : ImageButton = findViewById(R.id.shopBackBtn)
        shopBackBtn!!.setOnClickListener(this)

    }
    override fun onClick(v : View?) {

        when (v!!.id) {
            R.id.shopBackBtn -> {
                this.finish()
            }
        }
    }
}
