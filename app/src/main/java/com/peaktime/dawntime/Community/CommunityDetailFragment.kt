package com.peaktime.dawntime.Community


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
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
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.fragment_community_detail.*
import kotlinx.android.synthetic.main.fragment_community_detail.view.*
import kotlinx.android.synthetic.main.fragment_community_detail_input.view.*
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

    var communityDetailDatas: ArrayList<CommunityDetailData>? = ArrayList()
    var communityDetailReplyDatas: ArrayList<CommunityDetailData2>? = ArrayList()

    var index: Int = 0
    var replyIndex: Int = 0
    var reReplyParentNum = 0
    var reReplyMode: Boolean = false
    var inputEdit: EditText? = null

    var basicLayout: LinearLayout? = null
    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var v: View? = null

    var likeCount: TextView? = null
    var replyCount: TextView? = null
    var scrapCount: TextView? = null

    var fireImage: ImageView? = null
    var scrapImage: ImageView? = null

    var optionImageBtn: ImageButton? = null
    var inputLayout: View? = null

    var replyBtn: Button? = null
    var replyEdit: EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater!!.inflate(R.layout.fragment_community_detail, container, false)

        if(arguments != null){
            index = arguments.getInt("index")
        }
        v!!.community_backbtn!!.setOnClickListener {
            fragmentManager.popBackStack()
        }

        likeCount = v!!.findViewById(R.id.unfireContents)
        replyCount = v!!.findViewById(R.id.unreplyContents)
        scrapCount = v!!.findViewById(R.id.unscrapContents)

        fireImage = v!!.findViewById(R.id.detail_fire_image)
        scrapImage = v!!.findViewById(R.id.detail_scrap_image)

        optionImageBtn = v!!.findViewById(R.id.community_gitar)

        //아이템 추가
        basicLayout = v!!.findViewById(R.id.community_scroll)  // 기존뷰

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getDetail()

        fireImage!!.setOnClickListener {
            communityLike()
        }

        scrapImage!!.setOnClickListener {
            communityScrap()
        }

        optionImageBtn!!.setOnClickListener {
            val popup = PopupMenu(activity, v!!.community_gitar)//v는 클릭된 뷰를 의미
            val inflater = activity.menuInflater
            if (communityDetailDatas!!.get(0).writer_check == true) {
                activity.menuInflater.inflate(R.menu.community_menu2, popup.menu)
            } else {
                activity.menuInflater.inflate(R.menu.community_menu, popup.menu)
            }

            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

        var inflater = layoutInflater
        inputLayout = inflater.inflate(R.layout.fragment_community_detail_input, null)
        inputEdit = inputLayout!!.findViewById(R.id.reply_msg)
        inputEdit!!.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus) {
//                Toast.makeText(activity,"포커스 유 ",Toast.LENGTH_SHORT).show()
            } else {
                reReplyMode = false
//                Toast.makeText(activity,"포커스 무 ",Toast.LENGTH_SHORT).show()
            }
        }

        replyBtn = inputLayout!!.findViewById(R.id.reply_send)
        replyEdit = inputLayout!!.findViewById(R.id.reply_msg)
        replyBtn!!.reply_send.setOnClickListener {
            Log.i("numaaa", reReplyParentNum.toString())
            replyWrite(reReplyParentNum)
            reReplyParentNum = 0
            replyEdit!!.setText("")
        }



        return v!!
    }

    fun replyWrite(parentNum: Int) {
        var getContentList = networkService!!.replyWrite(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index, parentNum, replyEdit!!.text.toString())

        getContentList.enqueue(object : Callback<ReplyWriteResponse> {
            override fun onResponse(call: Call<ReplyWriteResponse>?, response: Response<ReplyWriteResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {
                        Toast.makeText(activity, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                        var ft = fragmentManager.beginTransaction()
                        ft.detach(this@CommunityDetailFragment).attach(this@CommunityDetailFragment).commit()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<ReplyWriteResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun communityLike() {
        var getContentList = networkService!!.communityLike(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index)

        getContentList.enqueue(object : Callback<CommunityLikeResponse> {
            override fun onResponse(call: Call<CommunityLikeResponse>?, response: Response<CommunityLikeResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success like")) {
                        fireImage!!.setImageResource(R.drawable.view_fire_red)
                    } else if (response.body().message.equals("success unlike")) {
                        fireImage!!.setImageResource(R.drawable.view_unfire_navy)
                    }
                    var ft = fragmentManager.beginTransaction()
                    ft.detach(this@CommunityDetailFragment).attach(this@CommunityDetailFragment).commit()
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun communityScrap() {

        var getContentList = networkService!!.communityScrap(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index)

        getContentList.enqueue(object : Callback<CommunityLikeResponse> {
            override fun onResponse(call: Call<CommunityLikeResponse>?, response: Response<CommunityLikeResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successfully registered scrap")) {
                        scrapImage!!.setImageResource(R.drawable.view_scrap_yellow)
                    } else if (response.body().message.equals("successfully deleted scrap")) {
                        scrapImage!!.setImageResource(R.drawable.view_unscrap_navy)
                    }
                    var ft = fragmentManager.beginTransaction()//새로고침
                    ft.detach(this@CommunityDetailFragment).attach(this@CommunityDetailFragment).commit()
                } else {
                    Log.i("status", "fail")
                }
            }
            override fun onFailure(call: Call<CommunityLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun getDetail() {
        var getContentList = networkService!!.getCommunityDetail(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, index)

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
                        scrapCount!!.text = communityDetailDatas!!.get(0).scrap_count.toString()

                        if (communityDetailDatas!!.get(0).user_like == true) {
                            detail_fire_image.setImageResource(R.drawable.view_fire_red)
                        } else
                            detail_fire_image.setImageResource(R.drawable.view_unfire_navy)
                        if (communityDetailDatas!!.get(0).user_scrap == true)
                            detail_scrap_image.setImageResource(R.drawable.view_scrap_yellow)
                        else
                            detail_scrap_image.setImageResource(R.drawable.view_unscrap_navy)

                        //동적 뷰 추가
//                        communityDetailDatas!!.get(0).board_images = ArrayList()
                        try {
                            for (i in 0..communityDetailDatas!!.get(0).board_images!!.size - 1) {
                                var inflater = layoutInflater
                                var imageLayout = inflater.inflate(R.layout.fragment_community_detail_imageitem, null)
                                var image: ImageView = imageLayout.findViewById(R.id.detail_image)
                                requestManager!!.load(communityDetailDatas!!.get(0).board_images!!.get(i)).into(image)
                                basicLayout!!.addView(imageLayout)
                            }
                        } catch (e: Exception) {
                        }

                        for (i in 0..communityDetailReplyDatas!!.size - 1) {
                            var inflater = layoutInflater
                            if (communityDetailReplyDatas!!.get(i).recom_check == true) {
                                var replyLayout = inflater.inflate(R.layout.fragment_community_detail_replyitem, null)

                                replyLayout.setOnLongClickListener(this@CommunityDetailFragment)

                                if (communityDetailReplyDatas!!.get(i).com_writer == 1) {
                                    replyLayout.reply_writer_text.text = "익명(글쓴이)"
                                } else
                                    replyLayout.reply_writer_text.text = "익명"

                                if (communityDetailReplyDatas!!.get(i).writer_check == true) {
                                    replyLayout.cancle_btn1.visibility = View.VISIBLE
                                    replyLayout.cancle_btn1.setOnClickListener {
                                        replyIndex = communityDetailReplyDatas!!.get(i).com_id
                                        replyDelete()
                                    }
                                } else
                                    replyLayout.cancle_btn1.visibility = View.GONE

                                replyLayout.reply_date_text.text = communityDetailReplyDatas!!.get(i).com_date
                                replyLayout.reply_contents_text.text = communityDetailReplyDatas!!.get(i).com_content
                                replyLayout.id = i
                                basicLayout!!.addView(replyLayout)
                            } else {
                                var replyLayout = inflater.inflate(R.layout.fragment_community_detail_replyitem2, null)

                                replyLayout.setOnLongClickListener(this@CommunityDetailFragment)

                                if (communityDetailReplyDatas!!.get(i).com_writer == 1) {
                                    replyLayout.reply2_writer_text.text = "익명(글쓴이)"
                                } else
                                    replyLayout.reply2_writer_text.text = "익명"

                                if (communityDetailReplyDatas!!.get(i).writer_check == true) {
                                    replyLayout.cancle_btn2.visibility = View.VISIBLE
                                    replyLayout.cancle_btn2.setOnClickListener {
                                        replyIndex = communityDetailReplyDatas!!.get(i).com_id
                                        replyDelete()
                                    }
                                } else
                                    replyLayout.cancle_btn2.visibility = View.GONE

                                replyLayout.reply2_date_text.text = communityDetailReplyDatas!!.get(i).com_date
                                replyLayout.reply2_contents_text.text = communityDetailReplyDatas!!.get(i).com_content
                                replyLayout.id = i
                                basicLayout!!.addView(replyLayout)
                            }
                        }
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

        val replyDialog2 = AlertDialog.Builder(context)
        val dialogView2 = View.inflate(context, R.layout.rereply_dialog, null)
        replyDialog.setView(dialogView2)
        val alertDialog2 = replyDialog.create()

        if (communityDetailReplyDatas!!.get(p0!!.id).com_parent == 0 && communityDetailReplyDatas!!.get(p0.id).writer_check == false) {
            alertDialog.show()
        } else if (communityDetailReplyDatas!!.get(p0.id).com_parent != 0 && communityDetailReplyDatas!!.get(p0.id).writer_check == false) {
            alertDialog2.show()
        }

        var sendreply = dialogView.findViewById<TextView>(R.id.sendreply)
        sendreply.setOnClickListener {
            reReplyMode = true
            reReplyParentNum = communityDetailReplyDatas!!.get(p0.id).com_id
            alertDialog.cancel()
            replyEdit!!.requestFocus()
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
                var intent = Intent(activity, CommunityMsgActivity::class.java)
                intent.putExtra("BOARD_ID", communityDetailDatas!!.get(0).board_id)

                startActivity(intent)
            }
            R.id.singo ->{
                Toast.makeText(context, "신고접수 되었습니다.", Toast.LENGTH_SHORT).show()
            }
            R.id.modify_community_detail -> {
                var intent = Intent(activity, CommunityWriteActicity::class.java)
                intent.putExtra("MODE", "modify")
                intent.putExtra("INDEX", index)
                startActivityForResult(intent, 0)
            }
            R.id.delete_community_detail -> {
                boardDelete()
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {

        }
    }

    fun boardDelete() {
        var getContentList = networkService!!.boardDelete(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, CommunityDeleteInstance(index))

        getContentList.enqueue(object : Callback<CommunityDeleteResponse> {
            override fun onResponse(call: Call<CommunityDeleteResponse>?, response: Response<CommunityDeleteResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("success")) {
                        ApplicationController.instance!!.makeToast("게시물이 삭제되었습니다.")

                        fragmentManager.popBackStack()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityDeleteResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun replyDelete() {

        var getContentList = networkService!!.replyDelete(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, CommunityReplyDeleteInstance(replyIndex))

        getContentList.enqueue(object : Callback<CommunityDeleteResponse2> {
            override fun onResponse(call: Call<CommunityDeleteResponse2>?, response: Response<CommunityDeleteResponse2>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("successful delete")) {
                        ApplicationController.instance!!.makeToast("댓글이 삭제되었습니다.")
                        var ft = fragmentManager.beginTransaction()
                        ft.detach(this@CommunityDetailFragment).attach(this@CommunityDetailFragment).commit()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunityDeleteResponse2>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
}
