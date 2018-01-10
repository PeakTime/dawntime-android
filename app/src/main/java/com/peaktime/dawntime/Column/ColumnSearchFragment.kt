package com.peaktime.dawntime.Column

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_column_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minhyoung on 2018. 1. 5..
 */
class ColumnSearchFragment : Fragment() {
    private var columnSearchResult : RecyclerView? = null
    private var columnSearchDatas : ArrayList<ColumnListData>? = null
    private var columnSearchAdapter : ColumnListAdapter? = null

    private var networkSerivce : NetworkService? = null
    private var requestManager : RequestManager? = null
    private var columnSearchEditText : EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_column_search,container,false)

        var column_title : String? = null


        networkSerivce = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        columnSearchEditText = v.findViewById(R.id.column_search_edittext)
        columnSearchEditText!!.imeOptions = EditorInfo.IME_ACTION_SEARCH

        columnSearchResult = v.findViewById(R.id.column_search_result)
        columnSearchResult!!.layoutManager = LinearLayoutManager(activity)
        columnSearchResult!!.addItemDecoration(RecyclerViewDecoration(15))

        columnSearchEditText!!.setOnEditorActionListener {v: TextView?, actionId: Int, event: KeyEvent? ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    column_title = columnSearchEditText!!.text.toString()//검색키워드 받아오기
//                        highPrice = highPriceEditText!!.text.toString()

                    //입력된 값이 하나라도 없으면 예외처리
                    if(column_title == null){
                        Toast.makeText(activity, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("aaaaaaaaa",column_title)
                        getColumnSearchResult(column_title!!)
                    }
                }
                else ->
                {


                    return@setOnEditorActionListener false
                }// 기본 엔터키 동작

            }// 검색 동작
            return@setOnEditorActionListener true
        }


        //enter누르면 검색창으로 이동


        v!!.column_search_cancel_btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }
        return v
    }

    fun getColumnSearchResult(column_title : String){
        val getContentList = networkSerivce!!.getColumnSearch(column_title)
        getContentList.enqueue(object : Callback<ColumnListResponse>{
            override fun onResponse(call: Call<ColumnListResponse>?, response: Response<ColumnListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().status.equals("success")){
                        columnSearchDatas = response!!.body().result
                        Log.e("aaaaa",columnSearchDatas!!.get(0).column_title)
                        columnSearchAdapter = ColumnListAdapter(columnSearchDatas,requestManager!!)
                        columnSearchAdapter!!.setOnItemClickListener(View.OnClickListener { v: View? ->
                            Log.e("aaaaaa","clcicclc")
                            var bundle = Bundle()
                            var fragment = ColumnFragment()
                            val fm = fragmentManager.beginTransaction()
                            var position = columnSearchResult!!.getChildLayoutPosition(v)
                            var column_id = columnSearchDatas!!.get(position).column_id

                            bundle.putInt("column_id",column_id)
                            fragment.arguments = bundle

                            fm.replace(R.id.column_search_layout,fragment)
                            fm.addToBackStack(null)
                            fm.commit()
                        })
                        columnSearchResult!!.adapter = columnSearchAdapter
                    }
                }
                else{

                }
            }

            override fun onFailure(call: Call<ColumnListResponse>?, t: Throwable?) {
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