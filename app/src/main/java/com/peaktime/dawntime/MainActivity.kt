package com.peaktime.dawntime

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.peaktime.dawntime.Community.CommunityActivity
import com.peaktime.dawntime.Shop.ShopActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_tab.addTab(main_tab.newTab().setIcon(R.drawable.ic_home))
        main_tab.addTab(main_tab.newTab().setIcon(R.drawable.ic_community))
        main_tab.addTab(main_tab.newTab().setIcon(R.drawable.ic_shop))
        main_tab.addTab(main_tab.newTab().setIcon(R.drawable.ic_mypage))

        if(savedInstanceState == null){
     //       val bundle = Bundle()
         //   bundle.putString("title",firstText.text.toString())
          //  Toast.makeText(this,"ttttt",Toast.LENGTH_LONG)
            AddFragment(HomeFragment(),"home")
        }



        var tabAdapter = TabAdapter(supportFragmentManager,main_tab.tabCount)

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0->{
                        val bundle = Bundle()
     //                   bundle.putString("title",firstText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                        ReplaceFragment(HomeFragment(),"home")
                    }
                    1->{
                        val bundle = Bundle()
//                        bundle.putString("title",secondText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                        ReplaceFragment(CommunityActivity(),"community")
                    }
                    2->{
                        val bundle = Bundle()
  //                      bundle.putString("title",thirdText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                        startActivity(Intent(applicationContext,ShopActivity::class.java))
                    }
                    3->{
                        val bundle = Bundle()
    //                    bundle.putString("title",fourthText.text.toString())
                        //AddFragment(FirstFragment(),bundle,"first",supportFragmentManager.findFragmentById(R.id.main_container))
                        ReplaceFragment(MyPageFragment(),"myPage")
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
