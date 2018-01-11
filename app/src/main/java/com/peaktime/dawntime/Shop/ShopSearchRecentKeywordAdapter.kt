package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import kotlinx.android.synthetic.main.recent_keword_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by xlsdn on 2018-01-08.
 */
class ShopSearchRecentKeywordAdapter(var dataList : ArrayList<String>?, var requestManagaer: RequestManager) : RecyclerView.Adapter<ShopSearchRecentKeywordViewHolder>() {

    private var mainView : View?=null
    private var onItemClick : View.OnClickListener? = null
    var networkService: NetworkService? = null


    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopSearchRecentKeywordViewHolder {
        mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.recent_keword_item, parent, false)
        mainView!!.setOnClickListener(onItemClick)
        return ShopSearchRecentKeywordViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopSearchRecentKeywordViewHolder?, position: Int) {
        holder!!.recentKeywordName.setText(dataList!!.get(position))

        networkService = ApplicationController.instance!!.networkService

        mainView!!.delete_btn.setOnClickListener{

//            Toast.makeText(mainView!!.context, dataList!!.get(position), Toast.LENGTH_SHORT).show()

            deleteKeyword(position)
            deleteItem(holder, position)
//            mainView!!.kind_name_textview.setText("바뀜")
        }


    }

    fun deleteKeyword(position: Int){
        var deleteKeyword = networkService!!.deleteKeyword(SharedPreferInstance.getInstance(mainView!!.context).getPreferString("TOKEN")!!, dataList!!.get(position))
        deleteKeyword.enqueue(object : Callback<KeywordDeleteResponse> {
            override fun onResponse(call: Call<KeywordDeleteResponse>?, response: Response<KeywordDeleteResponse>?) {
                if (response!!.isSuccessful) {
                    //pokemonList!!.layoutManager = LinearLayoutManager(this)
                    if (response.body().message.equals("successful delete keyword")) {


                        dataList!!.removeAt(position)
                        notifyItemRemoved(position)
                        Toast.makeText(mainView!!.context, "삭제되었습니다", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Log.d("fail","fail")

                }
            }
            override fun onFailure(call: Call<KeywordDeleteResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.v("checkNetwork","checkNetwork")
            }
        })
    }

    fun deleteItem(holder: ShopSearchRecentKeywordViewHolder?, position: Int){

    }
    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

}