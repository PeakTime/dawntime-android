package com.peaktime.dawntime.MyPage




import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.CommonData.communityDatas
import com.peaktime.dawntime.CommonData.mycommentData
import com.peaktime.dawntime.Community.CommunityAdapter
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.Community.CommunityList
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.mypage_my_written.*
import kotlinx.android.synthetic.main.mypage_my_written.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by 예은 on 2018-01-11.
 */
class ChildMypageMypost : Fragment(), View.OnClickListener {
    private var mywrittenList: RecyclerView? = null
    private var adapter: CommunityAdapter? = null
    private var commentadapter: ChildMypageMycommentAdapter? = null
    private var mypostData: ArrayList<CommunityList>? = null
    private var mycommentDatas: ArrayList<MypageMycommentData>? = null

    var commentclick : Int = 1
    var postclick : Int = 0

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater!!.inflate(R.layout.mypage_my_written, container, false)


        mywrittenList = v.findViewById(R.id.my_written_list)
        mywrittenList!!.layoutManager = LinearLayoutManager(activity)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        //뒤로가기
        v.my_written_btn.setOnClickListener{
            fragmentManager.popBackStack()
        }

        getMycommentList()

        v.mycomment_btn.setOnClickListener {
            commentclick = 1
            postclick = 0
            changeTextcolor()

            getMycommentList()
        }
        v.mypost_btn.setOnClickListener {
            commentclick = 0
            postclick = 1
            changeTextcolor()

            getMypostList()
        }
        val fm = fragmentManager
        fm.addOnBackStackChangedListener {
        }

        return v
    }
    fun changeTextcolor(){

        if(commentclick == 0 && postclick == 1) //내 글 눌렸을 때
        {
            my_reply_txt.setTextColor(Color.parseColor("#B5B8C8"))
            my_reply_cnt.setTextColor(Color.parseColor("#B5B8C8"))

            my_written_txt.setTextColor(Color.parseColor("#182E6E"))
            my_written_cnt.setTextColor(Color.parseColor("#182E6E"))
        }
        else //내 댓글 눌렸을 때
        {
            my_reply_txt.setTextColor(Color.parseColor("#182E6E"))
            my_reply_cnt.setTextColor(Color.parseColor("#182E6E"))

            my_written_txt.setTextColor(Color.parseColor("#B5B8C8"))
            my_written_cnt.setTextColor(Color.parseColor("#B5B8C8"))
        }

    }

    fun getMypostList() {
        var getContentList = networkService!!.getMypostList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<MyPageMypostResponse> {
            override fun onResponse(call: Call<MyPageMypostResponse>?, response: Response<MyPageMypostResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().status.equals("success")) {

                        mypostData = response.body().result
                        CommonData.communityDatas = mypostData

                        adapter = CommunityAdapter(mypostData, requestManager!!)
                        adapter!!.setOnItemClickListener(this@ChildMypageMypost)
                        mywrittenList!!.adapter = adapter

                    }
                } else {
                    Log.i("status", "fail")
                }
            }
            override fun onFailure(call: Call<MyPageMypostResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
    fun getMycommentList() {
        var getContentList = networkService!!.getMycommentList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<MyPageMycommentResponse> {
            override fun onResponse(call: Call<MyPageMycommentResponse>?, response: Response<MyPageMycommentResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("success")) {

                        my_written_cnt.text = response.body().myPost_count.toString()
                        my_reply_cnt.text = response.body().myCom_count.toString()
                        mycommentDatas = response.body().result
                        CommonData.mycommentData = mycommentDatas

                        commentadapter = ChildMypageMycommentAdapter(mycommentDatas, requestManager!!)
                        mywrittenList!!.adapter = commentadapter
                        commentadapter!!.setOnItemClickListener(View.OnClickListener { view: View? ->
                            val fm = fragmentManager.beginTransaction()
                            val fragment = CommunityDetailFragment()
                            val bundle = Bundle()
                            bundle.putInt("index", mycommentData!!.get(mywrittenList!!.getChildAdapterPosition(view!!)).board_id)
                            fragment.arguments = bundle
                            fm.add(R.id.mypage_mywritten_container,fragment,"detail")
                            fm.addToBackStack(null)
                            fm.commit()
                        })
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<MyPageMycommentResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    override fun onClick(p0: View?) {
        val fm = fragmentManager.beginTransaction()
        val fragment = CommunityDetailFragment()
        val bundle = Bundle()
        bundle.putInt("index", communityDatas!!.get(mywrittenList!!.getChildAdapterPosition(p0!!)).board_id)
        fragment.arguments = bundle
        fm.add(R.id.mypage_mywritten_container,fragment,"detail")
        fm.addToBackStack(null)
        fm.commit()
    }

}