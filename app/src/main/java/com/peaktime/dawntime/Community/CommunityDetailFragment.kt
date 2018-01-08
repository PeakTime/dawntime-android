package com.peaktime.dawntime.Community


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_community_detail.*
import kotlinx.android.synthetic.main.fragment_community_detail.view.*
import kotlinx.android.synthetic.main.fragment_community_detail_replyitem.view.*
import kotlinx.android.synthetic.main.fragment_community_detail_replyitem2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * Created by HYEON on 2018-01-03.
 */
class CommunityDetailFragment : Fragment(),PopupMenu.OnMenuItemClickListener,View.OnLongClickListener{

    var communityDetailDatas: ArrayList<CommunityDetailData>? = null
    var communityDetailReplyDatas: ArrayList<CommunityDetailData2>? = null

    var index: Int = 0

    var basicLayout: LinearLayout? = null
    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var v: View? = null

    var likeCount: TextView? = null
    var replyCount: TextView? = null
    var scrapCount: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater!!.inflate(R.layout.fragment_community_detail, container, false)

        if(arguments != null){
            index = arguments.getInt("index")
        }
        v!!.community_backbtn!!.setOnClickListener {

            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        likeCount = v!!.findViewById(R.id.unfireContents)
        replyCount = v!!.findViewById(R.id.unreplyContents)
        scrapCount = v!!.findViewById(R.id.unstarContents)

        //아이템 추가
        basicLayout = v!!.findViewById(R.id.community_scroll)  // 기존뷰

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getDetail()


//
//        community_unstarContents!!.setOnClickListener{
//            if(unstar_touch==0)
//            {
//                community_unstarContents!!.setImageResource(R.drawable.view_scrap_yellow)
//                unstar_touch = 1
//            }
//            else
//            {
//                community_unstarContents!!.setImageResource(R.drawable.view_unscrap_navy)
//                unstar_touch = 0
//            }
//
//        }
//        community_unfireContents!!.setOnClickListener{
//            if(unfire_touch==0)
//            {
//                community_unfireContents!!.setImageResource(R.drawable.view_fire_red)
//                unfire_touch = 1
//            }
//            else
//            {
//                community_unfireContents!!.setImageResource(R.drawable.view_unfire_navy)
//                unfire_touch = 0
//            }
//        }
//
//        v.community_gitar!!.setOnClickListener {
//            Log.v("community", "community")
//            val popup = PopupMenu(activity, v.community_gitar)//v는 클릭된 뷰를 의미
//            val inflater = activity.menuInflater
//            activity.menuInflater.inflate(R.menu.community_menu, popup.menu)
//            popup.setOnMenuItemClickListener(this)
//            popup.show()
//        }

//        //댓글작성
//        communityReplyDatas = ArrayList<CommunityReplyData>()
//        communityReplyList = v.findViewById(R.id.reply_list)
//        communityReplyList!!.layoutManager = LinearLayoutManager(activity)
//        v.reply_send!!.setOnClickListener{
//            var reply_content : String ?= null
//            reply_content = v.reply_msg.getText().toString()
//            reply_msg.setText("")
//            val now = System.currentTimeMillis()
//            val date = Date(now)
//            val sdf = SimpleDateFormat("MM/dd HH:mm")
//            val getTime = sdf.format(date)
//
//            communityReplyDatas!!.add(CommunityReplyData("익명",getTime, reply_content))
//            communityReplyAdapter = CommunityReplyAdapter(communityReplyDatas)
//            communityReplyList!!.adapter = communityReplyAdapter
//            communityReplyAdapter!!.setOnItemClickListener(this)
//        }

        return v!!
    }

    fun getDetail() {
        var getContentList = networkService!!.getCommunityDetail(index, CommunityDetailInstance(1))

        getContentList.enqueue(object : Callback<CommunityDetailResponse> {
            override fun onResponse(call: Call<CommunityDetailResponse>?, response: Response<CommunityDetailResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {
                        communityDetailDatas = response.body().boardResult
                        communityDetailReplyDatas = response.body().comResult

                        title.text = communityDetailDatas!!.get(0).board_title
                        contents.text = communityDetailDatas!!.get(0).board_content
                        detail_date.text = communityDetailDatas!!.get(0).board_date
                        detail_tab_text.text = communityDetailDatas!!.get(0).board_tag

                        likeCount!!.text = communityDetailDatas!!.get(0).board_like.toString()
                        replyCount!!.text = communityDetailDatas!!.get(0).com_count.toString()
//                        scrapCount!!.setText(communityDetailDatas!!.get(0).scrap_count.toString())
                        //아직 널로 들어오는거 같은데 서버에 검사 부탁해야할듯
                        if (communityDetailDatas!!.get(0).user_like == true) {
                            community_unfireContents.setImageResource(R.drawable.view_fire_red)
                        } else
                            community_unfireContents.setImageResource(R.drawable.view_unfire_navy)
                        if (communityDetailDatas!!.get(0).user_scrap == true)
                            community_unscrapContents.setImageResource(R.drawable.view_scrap_yellow)
                        else
                            community_unscrapContents.setImageResource(R.drawable.view_unscrap_navy)

                        //동적 뷰 추가
                        for (i in 0..communityDetailDatas!!.get(0).board_image.size - 1) {
                            var inflater = layoutInflater
                            var imageLayout = inflater.inflate(R.layout.fragment_community_detail_imageitem, null)
                            var image: ImageView = imageLayout.findViewById(R.id.detail_image)
                            requestManager!!.load(communityDetailDatas!!.get(0).board_image.get(i)).into(image)
                            basicLayout!!.addView(imageLayout)
                        }
                        for (i in 0..communityDetailReplyDatas!!.size - 1) {
                            var inflater = layoutInflater
                            if (communityDetailReplyDatas!!.get(i).recom_check == true) {
                                var replyLayout = inflater.inflate(R.layout.fragment_community_detail_replyitem, null)
                                if (communityDetailReplyDatas!!.get(i).writer_check == true) {
                                    replyLayout.reply_writer_text.text = "익명(글쓴이)"
                                } else
                                    replyLayout.reply_writer_text.text = "익명"
                                replyLayout.reply_date_text.text = communityDetailReplyDatas!!.get(i).com_date
                                replyLayout.reply_contents_text.text = communityDetailReplyDatas!!.get(i).com_content
                                basicLayout!!.addView(replyLayout)
                            } else {
                                var replyLayout = inflater.inflate(R.layout.fragment_community_detail_replyitem2, null)
                                if (communityDetailReplyDatas!!.get(i).writer_check == true) {
                                    replyLayout.reply2_writer_text.text = "익명(글쓴이)"
                                } else
                                    replyLayout.reply2_writer_text.text = "익명"
                                replyLayout.reply2_date_text.text = communityDetailReplyDatas!!.get(i).com_date
                                replyLayout.reply2_contents_text.text = communityDetailReplyDatas!!.get(i).com_content
                                basicLayout!!.addView(replyLayout)
                            }
                        }
                        var inflater = layoutInflater
                        var inputLayout = inflater.inflate(R.layout.fragment_community_detail_input, null)
                        basicLayout!!.addView(inputLayout)
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


    override fun onLongClick(p0: View?): Boolean {

        val replyDialog = AlertDialog.Builder(context)
        val dialogView = View.inflate(context,R.layout.reply_dialog,null)
        replyDialog.setView(dialogView)
        val alertDialog = replyDialog.create()
        alertDialog.show()

        var sendMsg = dialogView.findViewById<TextView>(R.id.sendMsg)
        sendMsg.setOnClickListener {
            val fm = activity.fragmentManager
            val transacton = fm.beginTransaction()
            val fragment = CommunityMsgFragment()
            transacton.add(R.id.community_detail_container, fragment, "msg")
            transacton.addToBackStack(null)
            alertDialog.cancel()
            transacton.commit()
        }
        var sendreply = dialogView.findViewById<TextView>(R.id.sendreply)
        sendreply.setOnClickListener {


        }
        var singo = dialogView.findViewById<TextView>(R.id.singo)
        singo.setOnClickListener {
            Toast.makeText(context, "신고접수 되었습니다.", Toast.LENGTH_SHORT).show()
            alertDialog.cancel()
        }
        return false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.itemId) {
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
            R.id.singo ->{
                //TODO : search 누르면 할 일
                Toast.makeText(context, "신고접수 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}
