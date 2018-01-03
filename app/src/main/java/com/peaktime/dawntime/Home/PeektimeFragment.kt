package com.peaktime.dawntime.Home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

class PeektimeFragment : Fragment() {

    private var peektimeRecycler : RecyclerView? = null
    private var peektimeDatas : ArrayList<PeektimeData>? = null
    private var peektimeAdapter : PeektimeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_peektime,container,false)

        peektimeDatas = ArrayList<PeektimeData>()

//        if(arguments.getBundle("dataList") == null){
//         Log.e("Error","arguments null")

        //}else {
          //  peektimeDatas = arguments.getBundle("dataList") as ArrayList<PeektimeData>
            peektimeDatas = PeektimeObject.peektimeDatas
            peektimeAdapter = PeektimeAdapter(peektimeDatas)

            peektimeRecycler = v.findViewById(R.id.peektime_recycler_list2)
            peektimeRecycler!!.layoutManager = GridLayoutManager(activity, 2)
            peektimeRecycler!!.adapter = peektimeAdapter
        //}
        return v
    }
}
