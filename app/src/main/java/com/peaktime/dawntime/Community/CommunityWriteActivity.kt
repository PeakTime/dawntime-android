package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peaktime.dawntime.R
import android.content.Intent
import android.provider.MediaStore.Images
import android.graphics.Bitmap
import android.app.Activity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.IOException
import kotlinx.android.synthetic.main.activity_community_write.*


class CommunityWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write)

        var community_addImg : LinearLayout ?= null
        community_addImg = findViewById(R.id.community_addImg)

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        //startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        Toast.makeText(baseContext, "resultCode : " + resultCode, Toast.LENGTH_SHORT).show()

        /*
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    val image_bitmap = Images.Media.getBitmap(contentResolver, data.data)
                    val image = findViewById(R.id.imageView1) as ImageView

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap)


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (e: FileNotFoundException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }*/

        community_backbtn1!!.setOnClickListener{
            finish()
        }
    }
}