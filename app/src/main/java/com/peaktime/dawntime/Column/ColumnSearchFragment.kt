package com.peaktime.dawntime.Column

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_column_search.view.*

/**
 * Created by minhyoung on 2018. 1. 5..
 */
class ColumnSearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_column_search,container,false)
        v!!.column_search_cancel_btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }
        return v
    }
}