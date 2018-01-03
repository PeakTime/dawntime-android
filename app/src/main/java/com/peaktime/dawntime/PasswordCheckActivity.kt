package com.peaktime.dawntime

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class PasswordCheckActivity : AppCompatActivity() {

    val MODE_SETTING = "setting"
    var image1: ImageView? = null
    var image2: ImageView? = null
    var image3: ImageView? = null
    var image4: ImageView? = null

    var list = ArrayList<String>()
    var order = 1
    var password: String? = null
    var inputNum = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_check)

        image1 = findViewById(R.id.password_circle_image1)
        image2 = findViewById(R.id.password_circle_image2)
        image3 = findViewById(R.id.password_circle_image3)
        image4 = findViewById(R.id.password_circle_image4)

        password = intent.getStringExtra("PASSWORD")

    }

    fun checkMatch() {
        if (password == inputNum) {

            SharedPreferInstance.getInstance(this).putPreferString("PASSWORD", password as String)
            setResult(Activity.RESULT_OK, null)
            finish()
        } else {
            order = 1
            list.clear()
            list.add("")
            inputNum = ""
            Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, LockActivity::class.java)
            intent.putExtra("MODE", MODE_SETTING)
            startActivity(intent)
            finish()
        }
    }

    fun addNum(inputNum: String) {
        this.inputNum += inputNum
        order++
        if (order == 5) {
            checkMatch()
        } else {
            list.add(this.inputNum)
        }
    }

    fun changeEvent(i: Int) {
        when (i) {
            1 -> {
                image1!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image2!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image3!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image4!!.setImageResource(R.drawable.view_nonfilledcircle_white)
            }
            2 -> {
                image1!!.setImageResource(R.drawable.view_filledcircle_white)
                image2!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image3!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image4!!.setImageResource(R.drawable.view_nonfilledcircle_white)
            }
            3 -> {
                image1!!.setImageResource(R.drawable.view_filledcircle_white)
                image2!!.setImageResource(R.drawable.view_filledcircle_white)
                image3!!.setImageResource(R.drawable.view_nonfilledcircle_white)
                image4!!.setImageResource(R.drawable.view_nonfilledcircle_white)
            }
            4 -> {
                image1!!.setImageResource(R.drawable.view_filledcircle_white)
                image2!!.setImageResource(R.drawable.view_filledcircle_white)
                image3!!.setImageResource(R.drawable.view_filledcircle_white)
                image4!!.setImageResource(R.drawable.view_nonfilledcircle_white)

            }
            5 -> {
                image1!!.setImageResource(R.drawable.view_filledcircle_white)
                image2!!.setImageResource(R.drawable.view_filledcircle_white)
                image3!!.setImageResource(R.drawable.view_filledcircle_white)
                image4!!.setImageResource(R.drawable.view_filledcircle_white)
            }
        }
    }

    fun btnClick(v: View) {
        when (v.id) {
            R.id.one_btn -> {

                addNum("1")
            }
            R.id.two_btn -> {
                addNum("2")
            }
            R.id.three_btn -> {
                addNum("3")
            }
            R.id.four_btn -> {
                addNum("4")
            }
            R.id.five_btn -> {
                addNum("5")
            }
            R.id.six_btn -> {
                addNum("6")
            }
            R.id.seven_btn -> {
                addNum("7")
            }
            R.id.eight_btn -> {
                addNum("8")
            }
            R.id.nine_btn -> {
                addNum("9")
            }
            R.id.zero_btn -> {
                addNum("0")
            }
            R.id.cancel_btn -> {
                finish()
            }
            R.id.delete_btn -> {
                if (order != 0) {
                    if (list.size > 1) {
                        order--
                        list.removeAt(order)
                        var temp = order - 1
                        inputNum = list.get(temp)
                    }
                }
            }
        }
        changeEvent(order)
    }
}
