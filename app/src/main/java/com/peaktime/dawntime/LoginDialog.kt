package com.peaktime.dawntime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import com.peaktime.dawntime.MyPage.LoginActivity

/**
 * Created by minhyoung on 2018. 1. 12..
 */
class LoginDialog : Activity(), View.OnClickListener {

    private var loginButton : View? = null
    private var cancelButton : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.custom_login_dialog)

        loginButton = findViewById(R.id.login_dialog_logBtn)
        cancelButton = findViewById(R.id.login_dialog_cancelbtn)

        loginButton!!.setOnClickListener(this)
        cancelButton!!.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.login_dialog_logBtn->{
                finish()
                startActivity(Intent(this,LoginActivity::class.java))
            }
            R.id.login_dialog_cancelbtn->{
                finish()
            }
        }
    }
}