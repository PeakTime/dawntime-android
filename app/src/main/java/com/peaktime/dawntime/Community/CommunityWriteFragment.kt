package com.peaktime.dawntime.Community


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_write.view.*

class CommunityWriteFragment : Fragment(){

//    var community_addImg : LinearLayout?= null
//    community_addImg = findViewById(R.id.community_addImg)
    private var communityReplyDatas: ArrayList<CommunityReplyData>? = null
    private var communityReplyAdapter: CommunityReplyAdapter?=null
    private var communityReplyList: RecyclerView? = null


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
        return v
    }

    //    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//
//        Toast.makeText(baseContext, "resultCode : " + resultCode, Toast.LENGTH_SHORT).show()
//
//        /*
//        if (requestCode == REQ_CODE_SELECT_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                try {
//                    //Uri에서 이미지 이름을 얻어온다.
//                    //String name_Str = getImageNameToUri(data.getData());
//
//                    //이미지 데이터를 비트맵으로 받아온다.
//                    val image_bitmap = Images.Media.getBitmap(contentResolver, data.data)
//                    val image = findViewById(R.id.imageView1) as ImageView
//
//                    //배치해놓은 ImageView에 set
//                    image.setImageBitmap(image_bitmap)
//
//
//                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
//
//
//                } catch (e: FileNotFoundException) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace()
//                } catch (e: IOException) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            }
//        }*/
//

//    }

}