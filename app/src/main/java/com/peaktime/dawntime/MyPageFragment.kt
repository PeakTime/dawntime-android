package com.peaktime.dawntime

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

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
            val fg = ChildMyPageOption()
            setChildFragment(fg)
        }

        return v
    }

    fun setChildFragment(child: Fragment) {
        var fm = childFragmentManager.beginTransaction()

        if (!child.isAdded) {
            fm.replace(R.id.child_container, child)
            fm.addToBackStack(null)//스택 쌓는 부분 수정필요할 듯
            fm.commit()
        }
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
    }


//    fun ableControls(enable : Boolean,vg : ViewGroup)
//    {
//        var i : Int
//        for( i in 0..vg.childCount)
//        {
//            var child =vg.getChildAt(i)
////            if(vg.getChildAt(i).id == R.id.frame)
////            {
////                continue
////            }
//            child.setEnabled(enable)
//
//
//            if(child is ViewGroup)
//                ableControls(enable,child)
//        }
//    }
}
