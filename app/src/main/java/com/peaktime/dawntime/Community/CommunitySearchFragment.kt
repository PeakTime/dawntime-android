package com.peaktime.dawntime.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 예은 on 2018-01-03.
 */
class CommunitySearchFragment : Fragment(), View.OnClickListener {
    private var communitySearchCancelBtn : ImageButton?= null
    var searchEdit: EditText? = null
    var contents: String? = null

    private var adapter: CommunityAdapter? = null
    private var communityList: RecyclerView? = null
    var contentsList: ArrayList<String>? = ArrayList()
    var searchList: ArrayList<CommunityList>? = ArrayList()
    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.activity_community_search,container,false)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        searchEdit = v.findViewById(R.id.communitySearchEditText)
        communityList = v.findViewById(R.id.search_recycler)
        communityList!!.layoutManager = LinearLayoutManager(activity)
        communitySearchCancelBtn = v.findViewById(R.id.communitySearchCancelBtn)

        communitySearchCancelBtn!!.setOnClickListener {

            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        searchEdit!!.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    communitySearch()
                }
            }
            if (!searchEdit!!.text.toString().equals("")) {
                communitySearch()
            } else
                Toast.makeText(activity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            false
        })

        return v
    }


    fun communitySearch() {
        contentsList = searchEdit!!.text.toString().split(" ") as ArrayList<String>

        try {
            if (contentsList!!.size == 1) {
                contentsList!!.add("defaultabcabcabc")
            }
        } catch (e: Exception) {
            Log.i("errror : ", "문자열 파싱 에러")
        }
        Log.i("넘길 문자열 ", contentsList!!.size.toString() + communityList!!.toString())

        var getContentList = networkService!!.communitySearch(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!, contentsList!!)
        getContentList.enqueue(object : Callback<CommunitySearchResponse> {
            override fun onResponse(call: Call<CommunitySearchResponse>?, response: Response<CommunitySearchResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().msg.equals("success")) {
                        searchList = response.body().result
                        Log.i("???", searchList.toString())
                        adapter = CommunityAdapter(searchList, requestManager!!)
                        adapter!!.setOnItemClickListener(this@CommunitySearchFragment)
                        communityList!!.adapter = adapter
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<CommunitySearchResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    override fun onClick(p0: View?) {

        val fm = fragmentManager.beginTransaction()
        val fragment = CommunityDetailFragment()
        val bundle = Bundle()
        bundle.putInt("index", searchList!!.get(communityList!!.getChildAdapterPosition(p0!!)).board_id)
        fragment.arguments = bundle
        fm.add(R.id.community_container, fragment, "detail")
        fm.addToBackStack(null)
        fm.commit()
    }

}