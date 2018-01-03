package com.peaktime.dawntime


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState

/**
 * Created by LEESANGYUN on 2018-01-01.
 */
class ChildMyPageOption : Fragment() {

    val MODE_SETTING = "setting"

    var mAuthLoginModule: OAuthLogin? = null
    var logoutBtn: TextView? = null
    var noticeSwitch: Switch? = null
    var lockSwitch: Switch? = null
    var blindSwitch: Switch? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var v = inflater!!.inflate(R.layout.child_mypage_option, container, false)

        logoutBtn = v!!.findViewById(R.id.logout_btn)
        noticeSwitch = v.findViewById(R.id.notice_switch)
        lockSwitch = v.findViewById(R.id.lock_switch)
        blindSwitch = v.findViewById(R.id.blind_switch)

        noticeSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("NOTICE")!!
        lockSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("LOCK")!!
        blindSwitch!!.isChecked = SharedPreferInstance.getInstance(activity).getPreferBoolean("BLIND")!!

        logoutBtn!!.setOnClickListener {

            mAuthLoginModule = OAuthLogin.getInstance()
            mAuthLoginModule!!.logout(activity)
            if (mAuthLoginModule!!.getState(activity) == OAuthLoginState.NEED_LOGIN) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOGIN", false)
                SharedPreferInstance.getInstance(activity).putPreferString("ID", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferString("GENDER", "로그인 정보 없음")
                SharedPreferInstance.getInstance(activity).putPreferString("EMAIL", "로그인 정보 없음")
                Toast.makeText(activity, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()
            }
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
//                var intent = Intent(activity,LockActivity::class.java)
//                intent.putExtra("MODE",MODE_SETTING)
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

        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(activity, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show()
            } else {
                lockSwitch!!.isChecked = false
                //비밀번호 설정 취소하면 다시 lock false로
            }
        }
    }
}