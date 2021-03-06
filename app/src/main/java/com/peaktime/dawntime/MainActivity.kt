package com.peaktime.dawntime

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.peaktime.dawntime.Community.CommunityFragment
import com.peaktime.dawntime.Home.HomeFragment
import com.peaktime.dawntime.MyPage.MyPageFragment
import com.peaktime.dawntime.Shop.ShopActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isLogin: Boolean? = null
    private var prePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.status_home)
        }

        //shared isLogin
        main_tab.removeAllTabs()
        //if(isLogin)
        main_tab.addTab(main_tab.newTab().setCustomView(R.layout.customtab_home))
        main_tab.addTab(main_tab.newTab().setCustomView(R.layout.customtab_comu))
        main_tab.addTab(main_tab.newTab().setCustomView(R.layout.customtab_shop))
        main_tab.addTab(main_tab.newTab().setCustomView(R.layout.customtab_my))

        if(savedInstanceState == null){
            ReplaceFragment(HomeFragment(),"home")
        }



        var tabAdapter = TabAdapter(supportFragmentManager,main_tab.tabCount)

        var tabStrip = main_tab.getChildAt(0) as LinearLayout
        tabStrip.getChildAt(2).setOnTouchListener(fun(v : View, event: MotionEvent) : Boolean{
            if(event.action == MotionEvent.ACTION_UP ){
//                ShopToMainActivity.bestFlagFun.bestFlag = 0
                if(SharedPreferInstance.getInstance(this@MainActivity).getPreferBoolean("LOGIN") == true) {
                    var intent = Intent(applicationContext, ShopActivity::class.java)
                    intent.putExtra("bestFlag", CommonData.CALL_AT_TAB_TO_SHOP)
                    startActivity(intent)
                }
                else{
                    startActivity(Intent(this,LoginDialog::class.java))
                }
            }
            return true
        })

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0->{
                        ReplaceFragment(HomeFragment(), "home")
                    }
                    1->{
                        if(SharedPreferInstance.getInstance(this@MainActivity).getPreferBoolean("LOGIN") == true) {
                            ReplaceFragment(CommunityFragment(), "community")
                        }
                        else{
                            //로그인 팝업
                            startActivity(Intent(this@MainActivity,LoginDialog::class.java))
                        }
                    }
                    3->{
                        ReplaceFragment(MyPageFragment(), "myPage")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                isLogin = SharedPreferInstance.getInstance(this@MainActivity).getPreferBoolean("LOGIN")
                when(tab!!.position){
                    0->{
                        val bundle = Bundle()
     //                   bundle.putString("title",firstText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                            ReplaceFragment(HomeFragment(), "home")
                        prePosition = 0
                    }
                    1->{
                        val bundle = Bundle()
//                        bundle.putString("title",secondText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                        if(isLogin!!) {
                            ReplaceFragment(CommunityFragment(), "community")
                        }
                        else{
                            //로그인 팝업
                            //이전 포지션으로 돌아가기
                            startActivity(Intent(applicationContext, LoginDialog::class.java))
                            //tab!!.position. = prePosition
                            // = prePosition
                            //R.id.

                            var tab2 = main_tab.getTabAt(prePosition)
                            //main_tab.getTabAt(0)
                            tab2!!.select()

                                //main_tab.removeTabAt(1)
                                ////main_tab.addTab(main_tab.newTab().setCustomView(R.layout.customtab_comu), 1)

                        }
                    }
                    2->{
                        if(isLogin!!) {
                            var intent = Intent(applicationContext, ShopActivity::class.java)
                            intent.putExtra("bestFlag", CommonData.CALL_AT_TAB_TO_SHOP)
                            startActivity(intent)
                        }
                        else{
                            //로그인 팝업
                        }
                    }
                    3->{
                        prePosition = 3
                        val bundle = Bundle()
    //                    bundle.putString("title",fourthText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                            ReplaceFragment(MyPageFragment(), "myPage")
                    }
                }
            }

        })

        var email = intent.getStringExtra("EMAIL")
        var gender = intent.getStringExtra("GENDER")
        if (email != null && gender != null) {

        }
    }

    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String, fragment2 : Fragment){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = null
        transaction.remove(fragment2)
        transaction.add(R.id.main_container,fragment,tag)
        transaction.commit()
    }

    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = null
        transaction.add(R.id.main_container,fragment,tag)
        transaction.commit()
    }

    fun AddFragment(fragment: Fragment, tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_container,fragment,tag)
        transaction.commit()
    }

    fun TagFragment(tag: String){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if(fragment != null){
            transaction.replace(R.id.main_container,fragment)
            transaction.commit()
        }
    }

    fun ReplaceFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = null
        transaction.replace(R.id.main_container,fragment,tag)
        transaction.commit()
    }

    fun ReplaceFragment(fragment: Fragment,tag : String){

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_container,fragment, tag)
        transaction.commit()
    }

    fun LoginCheck() {

    }

}
