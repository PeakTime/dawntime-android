package com.peaktime.dawntime

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget

class SplashActivity : AppCompatActivity() {

    val MODE_CHECK = "check"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var splashGIF = findViewById<ImageView>(R.id.splash_image)
        var gifImage = GlideDrawableImageViewTarget(splashGIF)
        Glide.with(this).load(R.drawable.dawntime_splash).into(gifImage)

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
        }, 2600)

    }
}
