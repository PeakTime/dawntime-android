package com.peaktime.dawntime.MyPage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minhyoung on 2018. 1. 12..
 */
class SignOutDialog : Activity(), View.OnClickListener {
    var networkService: NetworkService? = null
    private var signOutButton : View? = null
    private var cancelButton : TextView? = null
    var mAuthLoginModule: OAuthLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.custom_signout_dialog)
        networkService = ApplicationController.instance!!.networkService
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
                signOut()

            }
            R.id.signOut_dialog_cancelBtn->{
                finish()
            }
        }
    }

    fun signOut() {
        var getContentList = networkService!!.signOut(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!)

        getContentList.enqueue(object : Callback<MyPageSignOutResponse> {
            override fun onResponse(call: Call<MyPageSignOutResponse>?, response: Response<MyPageSignOutResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("successful delete user")) {
                        mAuthLoginModule = OAuthLogin.getInstance()
                        mAuthLoginModule!!.logout(this@SignOutDialog)
                        if (mAuthLoginModule!!.getState(this@SignOutDialog) == OAuthLoginState.NEED_LOGIN) {
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferBoolean("LOGIN", false)
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferString("ID", "로그인 정보 없음")
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferString("GENDER", "로그인 정보 없음")
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferString("EMAIL", "로그인 정보 없음")
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferString("TOKEN", "")
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferString("AGE", "로그인 정보 없음")
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferBoolean("LOCK", false)
                            SharedPreferInstance.getInstance(this@SignOutDialog).putPreferBoolean("NOTICE", false)
                            Toast.makeText(this@SignOutDialog, "회원탈퇴 되었습니다.", Toast.LENGTH_LONG).show()
                        }

                        setResult(RESULT_OK)
                        finish()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<MyPageSignOutResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
}