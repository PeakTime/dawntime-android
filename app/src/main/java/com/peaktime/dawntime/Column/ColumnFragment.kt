package com.peaktime.dawntime.Column

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R


class ColumnFragment : Fragment(),View.OnClickListener {

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
        columnAdapter!!.setOnItemClickListener(this)

        columnRecycler = v.findViewById(R.id.column_recycler_list)
        columnRecycler!!.layoutManager = LinearLayoutManager(activity)
        columnRecycler!!.adapter = columnAdapter

        return v
    }

    override fun onClick(v: View?) {

    }

}
