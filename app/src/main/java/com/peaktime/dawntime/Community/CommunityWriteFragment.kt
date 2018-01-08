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
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.peaktime.dawntime.Column.ColumnFragment
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_write.*
import kotlinx.android.synthetic.main.fragment_community_write.view.*
import java.io.FileNotFoundException
import java.io.IOException

class CommunityWriteFragment : Fragment(){

//    var community_addImg : LinearLayout?= null
//    community_addImg = findViewById(R.id.community_addImg)


//    final val REQUEST_CAMERA_TAKE: Int = 100
//    final val REQUEST_READ_TAKE: Int = 101
//    final val REQUEST_WRITE_TAKE: Int = 102
    final val REQUEST_ALL: Int = 103
    final val REQUEST_IMG: Int = 200
    private var imageList : ArrayList<Any>? = null
    private var takeImgRecycler : RecyclerView? = null
    private var takeImgAdapter : TakeImageAdapter? = null
    private var takeImgDatas : ArrayList<TakeImageData>? = null

    //var permissionCheck : PermissionChecker? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_community_write, container, false)
        var select_horsehead : String ?= null

         //TODO :  뭔가 눌렀을 떄(가령 버튼을 누른다든가) 발생할 일 같아 보임.
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
//        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        v.horsehead_button!!.setOnClickListener{

           val horseheadDialog = AlertDialog.Builder(context)
            val dialogView = inflater.inflate(R.layout.fragment_community_write_horseheaddialog, null)
            horseheadDialog.setView(dialogView)
            val alertDialog = horseheadDialog.create()
            alertDialog.show()


            var btn_horsehead0 = dialogView.findViewById<Button>(R.id.horsehead0)
            btn_horsehead0.setOnClickListener{
                select_horsehead = btn_horsehead0.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead1 = dialogView.findViewById<Button>(R.id.horsehead1)
            btn_horsehead1.setOnClickListener{
                select_horsehead = btn_horsehead1.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead2 = dialogView.findViewById<Button>(R.id.horsehead2)
            btn_horsehead2.setOnClickListener{
                select_horsehead = btn_horsehead2.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead3 = dialogView.findViewById<Button>(R.id.horsehead3)
            btn_horsehead3.setOnClickListener{
                select_horsehead = btn_horsehead3.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead4 = dialogView.findViewById<Button>(R.id.horsehead4)
            btn_horsehead4.setOnClickListener{
                select_horsehead = btn_horsehead4.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead5 = dialogView.findViewById<Button>(R.id.horsehead5)
            btn_horsehead5.setOnClickListener{
                select_horsehead = btn_horsehead5.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead6 = dialogView.findViewById<Button>(R.id.horsehead6)
            btn_horsehead6.setOnClickListener{
                select_horsehead = btn_horsehead6.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead7 = dialogView.findViewById<Button>(R.id.horsehead7)
            btn_horsehead7.setOnClickListener{
                select_horsehead = btn_horsehead7.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead8 = dialogView.findViewById<Button>(R.id.horsehead8)
            btn_horsehead8.setOnClickListener{
                select_horsehead = btn_horsehead8.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
            var btn_horsehead9 = dialogView.findViewById<Button>(R.id.horsehead9)
            btn_horsehead9.setOnClickListener{
                select_horsehead = btn_horsehead9.text.toString()
                alertDialog.cancel()
                v.horsehead_button.text = select_horsehead
                v.horsehead_button.setTextColor(Color.parseColor("#ffffff"))
                v.horsehead_button.setBackgroundResource(R.drawable.selectlinedrawable)
            }
        }

        v.community_backbtn1!!.setOnClickListener {
//            val fm = activity.fragmentManager
//            val transacton = fm.beginTransaction()
//            val fragment = CommunityDetailFragment()
//            transacton.remove(this)
//            transacton.commit()
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        v.picture_button!!.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                val permissions = Array<String>(3) { Manifest.permission.CAMERA;Manifest.permission.READ_EXTERNAL_STORAGE;Manifest.permission.WRITE_EXTERNAL_STORAGE }

                if (!hasPermissions(activity, permissions)) {
                    //요청하는 permission이 하나라도 없을때
                    //permission을 요청
                    requestPermissions(permissions, REQUEST_ALL)
                } else {
//                    val intent = Intent(Intent.ACTION_PICK)
//                    intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
//                    intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    startActivityForResult(intent, REQUEST_IMG)
                    getAlbum()
                }
            } else {
                //sdk 버전이 23이하 일경우
            }
        }
        return v
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
//            REQUEST_CAMERA_TAKE -> {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(activity, "permission CAMERA", Toast.LENGTH_LONG).show()
//                }
//            }
//            REQUEST_READ_TAKE -> {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(activity, "permission READ", Toast.LENGTH_LONG).show()
//                }
//            }
            REQUEST_ALL -> {
                Toast.makeText(activity, "permissions ALL", Toast.LENGTH_LONG).show()
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
        intent.setType(("image/*"))
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(Intent.createChooser(intent, "다중 선택은 '포토를 선택하세요"), REQUEST_IMG)
    }

//    fun imageDecode(bitmap : Bitmap) : Bitmap{
//        var option = BitmapFactory.Options()
//        option.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources,R.id.community_write_imageview,option)
//        var imageHeight = option.outHeight
//        var imageWidth = option.outWidth
//        var imageType = option.outMimeType
//
//    }

    fun calculateInSampleSize(options : BitmapFactory.Options,reqWidth : Int, reqHeight : Int) : Int{
        var height = options.outHeight
        var width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            var halfHeight = height / 2;
            var halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize
    }

    fun decodeSampledBitmapFromPath(resPath: String,
                                    reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(resPath, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(resPath,options)
    }

    fun getPathFromUri(uri : Uri) : String{
        var cursor = activity.contentResolver.query(uri,null,null,null,null)
        cursor.moveToNext()
        var path = cursor.getString(cursor.getColumnIndex("_data"))
        cursor.close()
        return path
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int,data: Intent?) {
        Log.e("Error","onActivity")
        if (requestCode == REQUEST_IMG) {
            Log.e("Error","Request Img")
            if (resultCode == Activity.RESULT_OK){
                Log.e("Error : ","Result Ok")
                try {
                    takeImgDatas = ArrayList<TakeImageData>()
                    if(data!!.clipData == null){
                        Log.i("take one Image","사진을 하나만 선택할 수 있습니다")
                        takeImgDatas!!.add(TakeImageData(MediaStore.Images.Media.getBitmap(activity.contentResolver,data.data)))
                    }
                    else{
                        var clipData = data.clipData
                        if(clipData.itemCount > 5) {
                            Toast.makeText(activity, "사진은 5개까지 선택 가능합니다.", Toast.LENGTH_LONG).show()
                        }
                        else if(clipData.itemCount == 1){
                            //val dataStr = clipData.getItemAt(0).uri.toString()
                            val dataStr = MediaStore.Images.Media.getBitmap(activity.contentResolver,clipData.getItemAt(0).uri)
                            //imageList!!.add(dataStr)
                            takeImgDatas!!.add(TakeImageData(dataStr))
                        }
                        else if(clipData.itemCount > 1 && clipData.itemCount <= 5){
                            var index = 0
                            while(index < clipData.itemCount){
                                takeImgDatas!!.add(TakeImageData(decodeSampledBitmapFromPath(getPathFromUri(clipData.getItemAt(index).uri),100,100)))
                                index++
                            }
                        }
                    }
                    takeImgAdapter = TakeImageAdapter(takeImgDatas)

                    takeImgRecycler = activity.findViewById(R.id.community_write_image_recycler)
                    val layoutManager = LinearLayoutManager(activity)
                    layoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
                    takeImgRecycler!!.layoutManager = layoutManager
                    takeImgRecycler!!.addItemDecoration(ColumnFragment.RecyclerViewDecoration(20))
                    takeImgRecycler!!.adapter = takeImgAdapter

                    if(takeImgDatas!!.size > 0){
                        picture_button!!.setTextColor(Color.parseColor("#ffffff"))
                        picture_button!!.setBackgroundResource(R.drawable.selectlinedrawable)
                    }
                    else{
                        picture_button!!.setTextColor(Color.parseColor("#231815"))
                        picture_button!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    }

                } catch (e: FileNotFoundException) {

                } catch (e: IOException) {

                } catch (e: Exception) {

                }

            }
            else{
                Toast.makeText(activity,"사진선택을 취소하였습니다.",Toast.LENGTH_LONG).show()
            }

        }
    }
}