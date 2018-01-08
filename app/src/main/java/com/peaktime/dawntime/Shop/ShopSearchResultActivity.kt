package com.peaktime.dawntime.Shop

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.peaktime.dawntime.R

class ShopSearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search_result)


//        var keyword : String = intent.getStringExtra("keyword")
//        Toast.makeText(this, keyword, Toast.LENGTH_SHORT).show()


        var intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}
