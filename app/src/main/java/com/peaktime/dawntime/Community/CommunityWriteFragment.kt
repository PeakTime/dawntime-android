package com.peaktime.dawntime.Community

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_write.view.*

class CommunityWriteFragment : Fragment(){

//    var community_addImg : LinearLayout?= null
//    community_addImg = findViewById(R.id.community_addImg)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_community_write, container, false)


         //TODO :  뭔가 눌렀을 떄(가령 버튼을 누른다든가) 발생할 일 같아 보임.
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
//        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI


        v.community_backbtn1!!.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fragment = CommunityDetailFragment()
            transacton.remove(this)
            transacton.commit()
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
//        community_backbtn1!!.setOnClickListener{
//            finish()
//        }
//    }

}