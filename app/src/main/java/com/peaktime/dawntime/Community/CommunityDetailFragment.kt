package com.peaktime.dawntime.Community

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_detail.view.*

/**
 * Created by HYEON on 2018-01-03.
 */
class CommunityDetailFragment : Fragment(), PopupMenu.OnMenuItemClickListener {


    private var communityDatas: ArrayList<CommunityData>? = null
    private var index : Int = 0

    var community_unstarContents : ImageView?= null
    var community_unfireContents : ImageView?= null
    var unstarContents : TextView?= null
    var unfireContents : TextView?= null
    var unstar_touch : Int ?= 0
    var unfire_touch : Int ?= 0

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

        community_unstarContents = v.findViewById(R.id.community_unscrapContents)
        community_unfireContents = v.findViewById(R.id.community_unfireContents)
        unstarContents = v.findViewById(R.id.unfireContents)
        unfireContents = v.findViewById(R.id.unscrapContents)

        community_unstarContents!!.setOnClickListener{
            if(unstar_touch==0)
            {
                community_unstarContents!!.setImageResource(R.drawable.view_scrap_yellow)
                unstar_touch = 1
            }
            else
            {
                community_unstarContents!!.setImageResource(R.drawable.view_unscrap_navy)
                unstar_touch = 0
            }

        }
        community_unfireContents!!.setOnClickListener{
            if(unfire_touch==0)
            {
                community_unfireContents!!.setImageResource(R.drawable.view_fire_red)
                unfire_touch = 1
            }
            else
            {
                community_unfireContents!!.setImageResource(R.drawable.view_unfire_navy)
                unfire_touch = 0
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

            R.id.sendMsg ->{

                //TODO : save 누르면 할 일
                val fm = activity.fragmentManager
                val transacton = fm.beginTransaction()
                val fragment = CommunityMsgFragment()
                transacton.add(R.id.community_detail_container, fragment, "msg")
                transacton.addToBackStack(null)
                transacton.commit()
            }
        //Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show()
            R.id.singo ->{
                //TODO : search 누르면 할 일
                Toast.makeText(context, "신고접수 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}
