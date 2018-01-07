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
import kotlinx.android.synthetic.main.fragment_column.view.*


class ColumnFragment : Fragment() {

    private var columnRecycler : RecyclerView? = null
    private var columnDatas : ArrayList<ColumnData>? = null
    private var columnAdapter : ColumnAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_column,container,false)

        columnDatas = ArrayList<ColumnData>()
        columnDatas!!.add(ColumnData(R.drawable.view_peakillu1_purple))
        columnDatas!!.add(ColumnData(R.drawable.view_peakillu2_green))
        columnDatas!!.add(ColumnData(R.drawable.view_peakillu3_violet))
        columnDatas!!.add(ColumnData(R.drawable.view_peakillu4_blue))

        columnAdapter = ColumnAdapter(columnDatas)

        columnRecycler = v.findViewById(R.id.card_news_recycler)
        columnRecycler!!.layoutManager = LinearLayoutManager(activity)
        columnRecycler!!.adapter = columnAdapter
        columnRecycler!!.addItemDecoration(RecyclerViewDecoration(15))

        v.column_back_btn.setOnClickListener{
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        return v
    }

    class RecyclerViewDecoration(var divHeight : Int?) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect!!.bottom = divHeight!!
        }
    }
}

