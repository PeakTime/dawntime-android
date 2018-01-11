package com.peaktime.dawntime.Shop

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.fragment.GoodsFragment
import com.peaktime.dawntime.Shop.fragment.GoodsSortFragment
import kotlinx.android.synthetic.main.activity_shop_to_main.*

class ShopToMainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_to_main)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.status_shop)
        }


        shopSearchBtn!!.setOnClickListener(this)

//        if(savedInstanceState == null){
            AddFragment(GoodsFragment()) //받은값으로 태그설정
//        }

    }

    //번들없는 함수
     fun AddFragment(fragment: Fragment){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        var bundle = Bundle()
        bundle.putInt("bestFlag", CommonData.CALL_AT_HOME_TO_SHOP)
        fragment.arguments = bundle
        transaction.add(R.id.shop_search_result_layout,fragment)
        transaction.commit()
    }


    override fun onClick(v : View?) {
        when (v!!.id) {

            R.id.shopSearchBtn -> {
//                Log.d("main","찍힘")
                var intent = Intent(applicationContext, ShopSearchActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
