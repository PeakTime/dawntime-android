package com.peaktime.dawntime.Column

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

class ColumnListFragment : Fragment(),View.OnClickListener {

    private var columnRecycler : RecyclerView? = null
    private var columnDatas : ArrayList<ColumnListData>? = null
    private var columnAdapter : ColumnListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_column_list,container,false)

        columnDatas = ArrayList<ColumnListData>()
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu1_purple,"test1","test!"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu2_green,"test2","test@"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu3_violet,"test3","test#"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu4_blue,"test4","test$"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu1_purple,"test5","test%"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu2_green,"test6","test^"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu3_violet,"test7","test&"))
        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu4_blue,"test8","test*"))

        columnAdapter = ColumnListAdapter(columnDatas)

        columnRecycler = v.findViewById(R.id.column_recycler_list)
        columnRecycler!!.layoutManager = LinearLayoutManager(activity)
        columnRecycler!!.adapter = columnAdapter
        columnRecycler!!.addItemDecoration(RecyclerViewDecoration(15))

        return v
    }

    override fun onClick(v: View?) {

    }

    class RecyclerViewDecoration(var divHeight : Int?) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.bottom = divHeight!!
        }
    }
}
