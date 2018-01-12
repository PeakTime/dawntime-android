package com.peaktime.dawntime.MyPage


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance


/**
 * Created by LEESANGYUN on 2018-01-01.
 */
class ChildMyPageOption : Fragment() {

    val MODE_SETTING = "setting"

    var mAuthLoginModule: OAuthLogin? = null
    var logoutBtn: RelativeLayout? = null
    var signoutBtn: RelativeLayout? = null
    var noticeSwitch: Switch? = null
    var lockSwitch: Switch? = null
    var blindSwitch: Switch? = null

    var backBtn: Button? = null

    var loginBoolean: Boolean? = false
    var networkService: NetworkService? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var v = inflater!!.inflate(R.layout.child_mypage_option, container, false)
        networkService = ApplicationController.instance!!.networkService
        logoutBtn = v!!.findViewById(R.id.logout_btn)
        signoutBtn = v.findViewById(R.id.sign_out_btn)
        noticeSwitch = v.findViewById(R.id.notice_switch)
        lockSwitch = v.findViewById(R.id.lock_switch)
        blindSwitch = v.findViewById(R.id.blind_switch)
        backBtn = v.findViewById(R.id.back_btn)

        noticeSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("NOTICE")!!
        lockSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("LOCK")!!
        blindSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("BLIND")!!

        backBtn!!.setOnClickListener {

            //            val fm = fragmentManager.beginTransaction()
//            fm.remove(this)
//            fm.commit()
            fragmentManager.popBackStack()
        }

        logoutBtn!!.setOnClickListener {

            mAuthLoginModule = OAuthLogin.getInstance()
            mAuthLoginModule!!.logout(activity)
            if (mAuthLoginModule!!.getState(activity) == OAuthLoginState.NEED_LOGIN) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOGIN", false)
                SharedPreferInstance.getInstance(activity).putPreferString("ID", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferString("GENDER", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferString("EMAIL", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferString("TOKEN", "")
                SharedPreferInstance.getInstance(activity).putPreferString("AGE", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", false)
                SharedPreferInstance.getInstance(activity).putPreferBoolean("NOTICE", false)
                Toast.makeText(activity, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()
            }

            val fm = fragmentManager.beginTransaction()
            fm.add(R.id.child_container, MyPageFragment(), "mypage")
            fm.commit()

            var fragment = fragmentManager
            fragment!!.popBackStack()
        }

        noticeSwitch!!.setOnClickListener {

            if (noticeSwitch!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("NOTICE", true)
                noticeSwitch!!.setThumbResource(R.drawable.switch_thumb_on)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("NOTICE", false)
                noticeSwitch!!.setThumbResource(R.drawable.switch_thumb)
            }
        }

        lockSwitch!!.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            if (b == true) {
                var intent = Intent(activity, LockActivity::class.java)
                intent.putExtra("MODE", MODE_SETTING)
                startActivityForResult(intent, 0)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", false)
                lockSwitch!!.setThumbResource(R.drawable.switch_thumb)
            }
        }

        lockSwitch!!.setOnClickListener {

            if (lockSwitch!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", true)
                lockSwitch!!.setThumbResource(R.drawable.switch_thumb_on)

            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", false)
                lockSwitch!!.setThumbResource(R.drawable.switch_thumb)
            }
        }

        blindSwitch!!.setOnClickListener {

            if (blindSwitch!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("BLIND", true)
                blindSwitch!!.setThumbResource(R.drawable.switch_thumb_on)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("BLIND", false)
                blindSwitch!!.setThumbResource(R.drawable.switch_thumb)
            }
        }
        signoutBtn!!.setOnClickListener {
            var intent = Intent(activity, SignOutDialog::class.java)
            startActivityForResult(intent, 1)
        }
        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(activity, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    loginBoolean = data!!.getBooleanExtra("LOGIN_BOOLEAN", false)
                    if (loginBoolean == true) {
                        Toast.makeText(activity, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    lockSwitch!!.isChecked = false
//                    //비밀번호 설정 취소하면 다시 lock false로
                }
            }
        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val fm = fragmentManager.beginTransaction()
                fm.add(R.id.child_container, MyPageFragment(), "mypage")
                fm.commit()

                var fragment = fragmentManager
                fragment!!.popBackStack()
            }
        }
    }


}