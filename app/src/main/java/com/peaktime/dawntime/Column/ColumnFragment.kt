package com.peaktime.dawntime.Column

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_column.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ColumnFragment : Fragment() {

    private var columnRecycler : RecyclerView? = null
    private var columnDatas : ArrayList<ColumnData>? = null
    private var columnAdapter : ColumnAdapter? = null

    private var cardNewsImageView : ImageView? = null
    private var cardNewsImage : String? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_column,container,false)
        val column_id = arguments.getInt("column_id")

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        cardNewsImageView = v.findViewById(R.id.card_news_img)


        getColumnDetail(column_id)

//        columnAdapter = ColumnAdapter(columnDatas,requestManager!!)
//
//        columnRecycler = v.findViewById(R.id.card_news_recycler)
//        columnRecycler!!.layoutManager = LinearLayoutManager(activity)
//        columnRecycler!!.adapter = columnAdapter
//        columnRecycler!!.addItemDecoration(RecyclerViewDecoration(15))

        v.column_back_btn.setOnClickListener{
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        return v
    }

    fun getColumnDetail(column_id : Int){
        val getContentList = networkService!!.getColumn(column_id)

        getContentList.enqueue(object : Callback<ColumnResponse>{
            override fun onResponse(call: Call<ColumnResponse>?, response: Response<ColumnResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        cardNewsImage = response.body().result

                        requestManager!!.load(cardNewsImage).into(cardNewsImageView)
                    }
                }
                else {
                    Log.i("status", "fail")
                }
            }
            override fun onFailure(call: Call<ColumnResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    class RecyclerViewDecoration(var divHeight : Int?) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect!!.bottom = divHeight!!
        }
    }
}

