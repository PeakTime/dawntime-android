package com.peaktime.dawntime.Community

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_detail.view.*

/**
 * Created by HYEON on 2018-01-03.
 */
class CommunityDetailFragment : Fragment(), PopupMenu.OnMenuItemClickListener {


    private var communityDatas: ArrayList<CommunityData>? = null
    private var index : Int = 0


//    var community_unstarContents : ImageView?= null
//    var community_unfireContents : ImageView?= null
//    var unstarContents : TextView?= null
//    var unfireContents : TextView?= null
//
//    community_unstarContents = findViewById(R.id.community_unstarContents)
//    community_unfireContents = findViewById(R.id.community_unfireContents)
//    unstarContents = findViewById(R.id.unfireContents)
//    unfireContents = findViewById(R.id.unstarContents)




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_community_detail,container,false)
        //communityDatas = ArrayList()
        if(arguments != null){
            index = arguments.getInt("index")
            //communityDatas = arguments.get("detailData") as ArrayList<CommunityData>

            v.community_backbtn!!.setOnClickListener{
                val fm = activity.fragmentManager
                val transacton = fm.beginTransaction()
                val fragment = CommunityDetailFragment()
                transacton.remove(this)
                transacton.commit()
            }
        }


        v.community_gitar!!.setOnClickListener {
            Log.v("community", "community")
        val popup = PopupMenu(activity, v.community_gitar)//v는 클릭된 뷰를 의미
        val inflater = activity.menuInflater
        activity.menuInflater.inflate(R.menu.community_menu, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }
        return v

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
        //눌러진 MenuItem의 Item Id를 얻어와 식별

            R.id.save ->{
                //TODO : save 누르면 할 일
            }
        //Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show()
            R.id.search ->{
                //TODO : search 누르면 할 일

            }
        //Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show()
            R.id.setting ->{
                //TODO : setting 누르면 할일

            }
        //Toast.makeText(this, "SETTING", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    /*community_unstarContents!!.setOnTouchListener(object : View.OnTouchListener {

    override fun onTouch(v: View, event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                unstarContents!!.text = unstarContents!!.text.toString() +1

            }

            MotionEvent.ACTION_MOVE -> {

            }

            MotionEvent.ACTION_CANCEL -> if (!mHasPerformedLongPress) {
                // This is a tap, so remove the longpress check
                removeLongPressCallback()
            }

            MotionEvent.ACTION_UP -> {
                Log.d("CLICK", "ACTION_UP")

                if (!mHasPerformedLongPress) {
                    // Long Click을 처리되지 않았으면 제거함.
                    removeLongPressCallback()

                    // Short Click 처리 루틴을 여기에 넣으면 됩니다.
                    performOneClick()

                }
            }

            else -> {
            }
        }

        return false
    }
})*/



}