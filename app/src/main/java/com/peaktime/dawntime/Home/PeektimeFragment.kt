package com.peaktime.dawntime.Home

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Column.ColumnFragment
import com.peaktime.dawntime.Community.*
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.fragment_peektime.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeektimeFragment : Fragment(),View.OnClickListener {

    private var peektimeRecycler : RecyclerView? = null
    private var peektimeDatas : ArrayList<CommunityList>? = null
    private var peektimeAdapter : PeektimeAdapter? = null

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_peektime,container,false)

        networkService = ApplicationController.instance!!.networkService


            peektimeRecycler = v.findViewById(R.id.peektime_recycler_list2)
            peektimeRecycler!!.layoutManager = GridLayoutManager(activity, 2)
            peektimeRecycler!!.adapter = peektimeAdapter
        peektimeRecycler!!.addItemDecoration(ReclerDeco(15))

        getPeaktimeList()

        v.peektime_back_btn!!.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        return v
    }

    fun getPeaktimeList(){
        val getContentList = networkService!!.getCommunityBestList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<CommunityResponse>{
            override fun onResponse(call: Call<CommunityResponse>?, response: Response<CommunityResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message.equals("success")){
                        peektimeDatas = response.body().result
                        peektimeAdapter = PeektimeAdapter(peektimeDatas)
                        peektimeAdapter!!.setOnItemClick(View.OnClickListener { v: View? ->
                            var board_id =
                                    peektimeDatas!!.get(peektimeRecycler!!.getChildLayoutPosition(v)).board_id
                            var bundle = Bundle()
                            bundle.putInt("index",board_id)
                            bundle.putString("FlagCheck","flagOn")
                            var fragment = CommunityDetailFragment()
                            fragment.arguments = bundle

                            var fm = fragmentManager.beginTransaction()
                            fm.replace(R.id.home_fragment_container,fragment)
                            fm.addToBackStack(null)
                            fm.commit()
                        })
                        peektimeRecycler!!.adapter = peektimeAdapter


                    }
                }
            }

            override fun onFailure(call: Call<CommunityResponse>?, t: Throwable?) {

            }

        })
    }

    override fun onClick(v: View?) {

    }

    class ReclerDeco(var divHeight : Int?) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.right = 50
            outRect!!.bottom = divHeight!!
        }
    }

}

