package com.peaktime.dawntime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class LockActivity : AppCompatActivity() {

    val MODE_SETTING = "setting"
    val MODE_CHECK = "check"

    var image1: ImageView? = null
    var image2: ImageView? = null
    var image3: ImageView? = null
    var image4: ImageView? = null

    var mode: String? = null
    var list = ArrayList<String>()
    var order = 1
    var password: String? = null
    var inputNum = ""
    var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        image1 = findViewById(R.id.password_circle_image1)
        image2 = findViewById(R.id.password_circle_image2)
        image3 = findViewById(R.id.password_circle_image3)
        image4 = findViewById(R.id.password_circle_image4)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        mode = intent.getStringExtra("MODE")

        list.add("")

        //패스워드 설정 전일경우
        if (mode == MODE_SETTING) {
            Toast.makeText(this, "비밀번호를 설정해주세요", Toast.LENGTH_SHORT).show()
        } else if (mode == MODE_CHECK) {
            password = SharedPreferInstance.getInstance(this).getPreferString("PASSWORD")
        }
    }

    fun checkMatch() {
        if (mode == MODE_CHECK) {
            if (password == inputNum) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                order = 1
                list.clear()
                list.add("")
                inputNum = ""
                vibrator!!.vibrate(300)

                Toast.makeText(this, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        } else if (mode == MODE_SETTING) {
            var intent = Intent(this, PasswordCheckActivity::class.java)
            intent.putExtra("PASSWORD", inputNum)
            intent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
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
        //    changeEvent(order)
//        var j = 0
//        for (j in 0..list.size - 1) {
//            Log.i("ㄴ어ㅣㄴ렁", j.toString() + " : " + list.get(j))
//        }
//        Log.i("inputNum", inputNum)
//        Log.i("order", order.toString())
//        Log.i("size", list.size.toString())
    }

    override fun onDestroy() {
        list.clear()
        order = 1
        inputNum = ""
        super.onDestroy()
    }
}
