package com.peaktime.dawntime.MyPage

import android.app.Activity.RESULT_OK

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance


class MyPageFragment : Fragment() {

    var view1: View? = null
    var view2: View? = null
    var view3: View? = null
    var view4: View? = null
    var view5: View? = null
    var view6: View? = null

    var image: ImageView? = null
    var myWrittenBtn: Button? = null
    var messageBtn: Button? = null
    var scrapBtn: Button? = null
    var basketBtn: Button? = null
    var optionBtn: Button? = null

    var loginBtn: Button? = null
    var loginText: TextView? = null
    var emailText: TextView? = null

    var v: View? = null

//    var mPref : SharedPreferences? = null
//    var changeListener : SharedPreferences.OnSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
//        run {
//            Log.i("sdjfkl","되써되써되서")
//            var b = SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")
//            if (b == null)
//                b = false
//
//            setEnable(b)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.fragment_mypage, container, false)

        image = v!!.findViewById(R.id.image)
        myWrittenBtn = v!!.findViewById(R.id.my_written_btn)
        messageBtn = v!!.findViewById(R.id.message_btn)
        scrapBtn = v!!.findViewById(R.id.scrap_btn)
        basketBtn = v!!.findViewById(R.id.basket_btn)
        optionBtn = v!!.findViewById(R.id.option_btn)

        loginBtn = v!!.findViewById(R.id.login_btn)
        loginText = v!!.findViewById(R.id.login_text)
        emailText = v!!.findViewById(R.id.email_text)

        view1 = v!!.findViewById(R.id.view1)
        view2 = v!!.findViewById(R.id.view2)
        view3 = v!!.findViewById(R.id.view3)
        view4 = v!!.findViewById(R.id.view4)
        view5 = v!!.findViewById(R.id.view5)
        view6 = v!!.findViewById(R.id.view6)

//        mPref = activity.getSharedPreferences("dawntime_sharedPreference",Context.MODE_PRIVATE)
//        mPref!!.registerOnSharedPreferenceChangeListener(changeListener)

        var b = SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")
        if (b == null)
            b = false

        setEnable(b)

        if (b == true)
            emailText!!.text = SharedPreferInstance.getInstance(activity).getPreferString("EMAIL")

        loginBtn!!.setOnClickListener {

            var intent = Intent(activity, LoginActivity::class.java)
            startActivityForResult(intent, 0)
        }
        optionBtn!!.setOnClickListener {

            val fm = fragmentManager.beginTransaction()
            fm.add(R.id.child_container, ChildMyPageOption(), "option")
            fm.addToBackStack(null)
            fm.commit()
        }
        messageBtn!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fg = ChildMyPageMessageBox()
            transacton.add(R.id.child_container, fg, "message")
            transacton.addToBackStack(null)
            transacton.commit()
        }
        scrapBtn!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fg = ChildMyPageMessageBox()
            transacton.add(R.id.child_container, fg, "message")
            transacton.addToBackStack(null)
            transacton.commit()
        }

        basketBtn!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fg = ChildMypageBasket()
            transacton.add(R.id.child_container, fg, "basket")
            transacton.addToBackStack(null)
            transacton.commit()
        }

        myWrittenBtn!!.setOnClickListener {

        }

        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            setEnable(true)
            emailText!!.text = data!!.getStringExtra("EMAIL")
        }
    }

    fun setEnable(b: Boolean) {
        if (b == false) {
            loginBtn!!.visibility = View.VISIBLE
            loginText!!.visibility = View.VISIBLE
            emailText!!.visibility = View.INVISIBLE
        } else {
            loginBtn!!.visibility = View.INVISIBLE
            loginText!!.visibility = View.INVISIBLE
            emailText!!.visibility = View.VISIBLE
        }

        image!!.isEnabled = b
        myWrittenBtn!!.isEnabled = b
        messageBtn!!.isEnabled = b
        scrapBtn!!.isEnabled = b
        basketBtn!!.isEnabled = b
        optionBtn!!.isEnabled = b

        view1!!.isEnabled = b
        view2!!.isEnabled = b
        view3!!.isEnabled = b
        view4!!.isEnabled = b
        view5!!.isEnabled = b
        view6!!.isEnabled = b

        if (b)//활성화 상태
        {
            image!!.setImageResource(R.drawable.view_activeprofile_navy)
            myWrittenBtn!!.setTextColor(Color.parseColor("#0E1949"))
            messageBtn!!.setTextColor(Color.parseColor("#0E1949"))
            scrapBtn!!.setTextColor(Color.parseColor("#0E1949"))
            basketBtn!!.setTextColor(Color.parseColor("#ED508E"))
            optionBtn!!.setTextColor(Color.parseColor("#4F4F4F"))
            view1!!.setBackgroundColor(Color.parseColor("#4D001960"))
            view2!!.setBackgroundColor(Color.parseColor("#4D001960"))
            view3!!.setBackgroundColor(Color.parseColor("#4D001960"))
            view4!!.setBackgroundColor(Color.parseColor("#4D001960"))
            view5!!.setBackgroundColor(Color.parseColor("#ED508E"))
            view6!!.setBackgroundColor(Color.parseColor("#4D4F4F4F"))

        } else {
            image!!.setImageResource(R.drawable.view_unactiveprofile_gray)
            myWrittenBtn!!.setTextColor(Color.parseColor("#B7B7B7"))
            messageBtn!!.setTextColor(Color.parseColor("#B7B7B7"))
            scrapBtn!!.setTextColor(Color.parseColor("#B7B7B7"))
            basketBtn!!.setTextColor(Color.parseColor("#B7B7B7"))
            optionBtn!!.setTextColor(Color.parseColor("#B7B7B7"))
            view1!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
            view2!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
            view3!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
            view4!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
            view5!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
            view6!!.setBackgroundColor(Color.parseColor("#CCCCCC"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("qwerty", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("qwerty", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("qwerty", "onResume")
    }

}
