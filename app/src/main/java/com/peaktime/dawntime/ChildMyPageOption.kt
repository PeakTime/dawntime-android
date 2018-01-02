package com.peaktime.dawntime


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState


/**
 * Created by LEESANGYUN on 2018-01-01.
 */
class ChildMyPageOption : Fragment() {

    var mAuthLoginModule: OAuthLogin? = null

    var logoutBtn: TextView? = null
    var noticeToggle: ToggleButton? = null
    var lockToggle: ToggleButton? = null
    var blindToggle: ToggleButton? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var v = inflater!!.inflate(R.layout.child_mypage_option, container, false)

        logoutBtn = v!!.findViewById(R.id.logout_btn)
        noticeToggle = v.findViewById(R.id.notice_toggle)
        lockToggle = v.findViewById(R.id.lock_toggle)
        blindToggle = v.findViewById(R.id.blind_toggle)

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

        noticeToggle!!.setOnClickListener {

            if (noticeToggle!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("NOTICE", true)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("NOTICE", false)
            }
        }

        lockToggle!!.setOnClickListener {

            if (lockToggle!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", true)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("LOCK", false)
            }
        }

        blindToggle!!.setOnClickListener {

            if (blindToggle!!.isChecked) {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("BLIND", true)
            } else {
                SharedPreferInstance.getInstance(activity).putPreferBoolean("BLIND", false)
            }
        }

        return v
    }
}