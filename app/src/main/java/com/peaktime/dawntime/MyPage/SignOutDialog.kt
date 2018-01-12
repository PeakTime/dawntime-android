package com.peaktime.dawntime.MyPage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import com.peaktime.dawntime.Home.PeektimeObject
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 12..
 */
class SignOutDialog : Activity(), View.OnClickListener {

    private var signOutButton : View? = null
    private var cancelButton : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.custom_login_dialog)

        signOutButton = findViewById(R.id.signOut_dialog_outBtn)
        cancelButton = findViewById(R.id.signOut_dialog_cancelBtn)

        signOutButton!!.setOnClickListener(this)
        cancelButton!!.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.signOut_dialog_outBtn->{
                //회원탈퇴 클릭시
            }
            R.id.signOut_dialog_cancelBtn->{
                finish()
            }
        }
    }
}