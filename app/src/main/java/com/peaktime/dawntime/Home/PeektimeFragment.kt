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
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.Community.CommunityFragment
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_peektime.view.*

class PeektimeFragment : Fragment(),View.OnClickListener {

    private var peektimeRecycler : RecyclerView? = null
    private var peektimeDatas : ArrayList<PeektimeData>? = null
    private var peektimeAdapter : PeektimeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_peektime,container,false)

        var networkService: NetworkService? = null
        var requestManager: RequestManager? = null

        peektimeDatas = ArrayList<PeektimeData>()

        peektimeDatas = PeektimeObject.peektimeDatas
        peektimeAdapter = PeektimeAdapter(peektimeDatas)
        peektimeAdapter!!.setOnItemClick(this)

            peektimeRecycler = v.findViewById(R.id.peektime_recycler_list2)
            peektimeRecycler!!.layoutManager = GridLayoutManager(activity, 2)
            peektimeRecycler!!.adapter = peektimeAdapter
        peektimeRecycler!!.addItemDecoration(ReclerDeco(15))

        v.peektime_back_btn!!.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        return v
    }

    override fun onClick(v: View?) {
        var board_id =
                peektimeDatas!!.get(peektimeRecycler!!.getChildLayoutPosition(v)).board_id
        var bundle = Bundle()
        bundle.putInt("index",board_id)
        var fragment = CommunityDetailFragment()
        fragment.arguments = bundle

        var fm = fragmentManager.beginTransaction()
        fm.replace(R.id.home_fragment_container,fragment)
        fm.addToBackStack(null)
        fm.commit()

    }

    class ReclerDeco(var divHeight : Int?) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.right = 50
            outRect!!.bottom = divHeight!!
        }
    }

}

