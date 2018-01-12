package com.peaktime.dawntime.Community

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.activity_community_write_acticity.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class CommunityWriteActicity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    private var data: Uri? = null
    private var image: MultipartBody.Part? = null
    var imageArray: ArrayList<MultipartBody.Part>? = ArrayList()

    var modifyData: ArrayList<CommunityDetailData>? = ArrayList()
    var modifyImage: ArrayList<String>? = ArrayList()

    var requestManager: RequestManager? = null
    private var networkService: NetworkService? = null
    var select_horsehead: String? = null

    val REQUEST_ALL: Int = 103

    var mode: String? = null
    var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write_acticity)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.status_home)
        }

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)
        mode = intent.getStringExtra("MODE")
        index = intent.getIntExtra("INDEX", 0)

        picture_button!!.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                val permissions = Array<String>(3) { Manifest.permission.CAMERA;Manifest.permission.READ_EXTERNAL_STORAGE;Manifest.permission.WRITE_EXTERNAL_STORAGE }

                if (!hasPermissions(this, permissions)) {
                    //요청하는 permission이 하나라도 없을때
                    //permission을 요청
                    requestPermissions(permissions, REQUEST_ALL)
                } else {
                    getAlbum()
                }
            } else {
                //sdk 버전이 23이하 일경우
            }
        }
        if (mode == "modify") {
            getDetail()
        }
        write_complete!!.setOnClickListener {

            if (!horsehead_button.text.toString().equals("머릿말 선택")) {
                if (mode == "modify") {
                    modifyDetail()
                } else {
                    boardWrite()
                }
            } else
                Toast.makeText(this, "머릿말을 선택해주세요", Toast.LENGTH_SHORT).show()

        }

        horsehead_button!!.setOnClickListener {

            val horseheadDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_community_write_horseheaddialog, null)
            horseheadDialog.setView(dialogView)
            val alertDialog = horseheadDialog.create()
/*

//            WindowManager.LayoutParams wm = WindowManager.LayoutParams();
                wm.copyFrom(dialog.getWindow().getAttributes());
                wm.width=200;
                wm.height=200;
*/

            alertDialog.show()

            var btn_horsehead0 = dialogView.findViewById<Button>(R.id.horsehead0)
            btn_horsehead0.setOnClickListener {
                select_horsehead = btn_horsehead0.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead1 = dialogView.findViewById<Button>(R.id.horsehead1)
            btn_horsehead1.setOnClickListener {
                select_horsehead = btn_horsehead1.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead2 = dialogView.findViewById<Button>(R.id.horsehead2)
            btn_horsehead2.setOnClickListener {
                select_horsehead = btn_horsehead2.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead3 = dialogView.findViewById<Button>(R.id.horsehead3)
            btn_horsehead3.setOnClickListener {
                select_horsehead = btn_horsehead3.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead4 = dialogView.findViewById<Button>(R.id.horsehead4)
            btn_horsehead4.setOnClickListener {
                select_horsehead = btn_horsehead4.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead5 = dialogView.findViewById<Button>(R.id.horsehead5)
            btn_horsehead5.setOnClickListener {
                select_horsehead = btn_horsehead5.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead6 = dialogView.findViewById<Button>(R.id.horsehead6)
            btn_horsehead6.setOnClickListener {
                select_horsehead = btn_horsehead6.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead7 = dialogView.findViewById<Button>(R.id.horsehead7)
            btn_horsehead7.setOnClickListener {
                select_horsehead = btn_horsehead7.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead8 = dialogView.findViewById<Button>(R.id.horsehead8)
            btn_horsehead8.setOnClickListener {
                select_horsehead = btn_horsehead8.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead9 = dialogView.findViewById<Button>(R.id.horsehead9)
            btn_horsehead9.setOnClickListener {
                select_horsehead = btn_horsehead9.text.toString()
                alertDialog.cancel()
                horsehead_button.text = select_horsehead
                horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
        }
        community_backbtn1!!.setOnClickListener {
            finish()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            REQUEST_ALL -> {
                Toast.makeText(this, "permissions ALL", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    fun getAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "사진(Photo)를 선택해주세요"), REQ_CODE_SELECT_IMAGE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    image1.setImageBitmap(null)
                    image2.setImageBitmap(null)
                    image3.setImageBitmap(null)
                    image4.setImageBitmap(null)
                    image5.setImageBitmap(null)

                    for (i in 0..(data!!.clipData.itemCount - 1)) {

                        val uri = data.clipData.getItemAt(i).uri

                        if (i == 0)
                            Glide.with(this).load(uri).centerCrop().into(image1)
                        else if (i == 1)
                            Glide.with(this).load(uri).centerCrop().into(image2)
                        else if (i == 2)
                            Glide.with(this).load(uri).centerCrop().into(image3)
                        else if (i == 3)
                            Glide.with(this).load(uri).centerCrop().into(image4)
                        else if (i == 4)
                            Glide.with(this).load(uri).centerCrop().into(image5)

                        val options = BitmapFactory.Options()

                        var input: InputStream? = null
                        try {
                            input = contentResolver.openInputStream(uri)
                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        }

                        val bitmap = BitmapFactory.decodeStream(input, null, options)
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                        val photo = File(this.data.toString())

                        image = MultipartBody.Part.createFormData("image", photo.name, photoBody)

                        imageArray!!.add(image!!)
                    }
                    Log.v("이미지", this.data.toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun boardWrite() {
        val board_title = RequestBody.create(MediaType.parse("text/plain"), b_title.text.toString())
        val board_content = RequestBody.create(MediaType.parse("text/plain"), contents.text.toString())
        val board_tag = RequestBody.create(MediaType.parse("text/plain"), horsehead_button.text.toString())

        var getContentList = networkService!!.boardWrite(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!, board_title, board_content, board_tag, imageArray)

        getContentList.enqueue(object : Callback<CommunityWriteResponse> {
            override fun onResponse(call: Call<CommunityWriteResponse>?, response: Response<CommunityWriteResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("successful")) {
                        ApplicationController.instance!!.makeToast("게시글이 등록되었습니다.")
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityWriteResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun modifyDetail() {
        val board_title = RequestBody.create(MediaType.parse("text/plain"), b_title.text.toString())
        val board_content = RequestBody.create(MediaType.parse("text/plain"), contents.text.toString())
        val board_tag = RequestBody.create(MediaType.parse("text/plain"), horsehead_button.text.toString())
        var board_id = RequestBody.create(MediaType.parse("text/plain"), index.toString())

        var getContentList = networkService!!.modifyDetail(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!, board_title, board_content, board_tag, board_id, imageArray)

        getContentList.enqueue(object : Callback<CommunityWriteResponse> {
            override fun onResponse(call: Call<CommunityWriteResponse>?, response: Response<CommunityWriteResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("success")) {
                        ApplicationController.instance!!.makeToast("게시글이 수정되었습니다.")
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityWriteResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })

    }

    fun getDetail() {
        var getContentList = networkService!!.getCommunityDetail(SharedPreferInstance.getInstance(this).getPreferString("TOKEN")!!, index!!)

        getContentList.enqueue(object : Callback<CommunityDetailResponse> {
            override fun onResponse(call: Call<CommunityDetailResponse>?, response: Response<CommunityDetailResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {
                        modifyData = response.body().boardResult
                        b_title.setText(modifyData!!.get(0).board_title)
                        contents.setText(modifyData!!.get(0).board_content)
                        horsehead_button.text = modifyData!!.get(0).board_tag

                        for (i in 0..(modifyData!!.get(0).board_images!!.size - 1)) {
                            if (i == 0)
                                requestManager!!.load(modifyData!!.get(0).board_images!!.get(i)).into(image1)
                            else if (i == 1)
                                requestManager!!.load(modifyData!!.get(0).board_images!!.get(i)).into(image2)
                            else if (i == 2)
                                requestManager!!.load(modifyData!!.get(0).board_images!!.get(i)).into(image3)
                            else if (i == 3)
                                requestManager!!.load(modifyData!!.get(0).board_images!!.get(i)).into(image4)
                            else if (i == 4)
                                requestManager!!.load(modifyData!!.get(0).board_images!!.get(i)).into(image5)
                        }
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

}
