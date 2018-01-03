package com.peaktime.dawntime

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    val MODE_CHECK = "check"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({

            if (SharedPreferInstance.getInstance(this).getPreferBoolean("LOCK")!!) {
                var intent = Intent(applicationContext, LockActivity::class.java)
                intent.putExtra("MODE", MODE_CHECK)
                startActivity(intent)
            } else {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            finish()
        }, 2000)

    }
}
