package com.peaktime.dawntime.Community

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.CommunityDetailFragment


/**
 * Created by HYEON on 2017-12-31.
 */
class CommunityFragment : Fragment(), View.OnClickListener {

    private var onItemClick: View.OnClickListener? = null

    private var communityList: RecyclerView? = null
    private var communityDatas: ArrayList<CommunityData>? = null
    private var adapter: CommunityAdapter? = null
    private var community_write: ImageButton? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_community, container, false)

        communityList = v.findViewById(R.id.main_list)
        community_write = v.findViewById(R.id.community_write)


        communityList!!.layoutManager = LinearLayoutManager(activity)

        communityDatas = ArrayList<CommunityData>()
        communityDatas!!.add(CommunityData(R.drawable.brown, "one", "원", "브라운"))
        communityDatas!!.add(CommunityData(R.drawable.cony, "two", "투", "코니"))
        communityDatas!!.add(CommunityData(R.drawable.selly, "three", "삼", "샐리"))
        communityDatas!!.add(CommunityData(R.drawable.brown, "one", "원", "브라운"))
        communityDatas!!.add(CommunityData(R.drawable.cony, "two", "투", "코니"))
        communityDatas!!.add(CommunityData(R.drawable.brown, "one", "원", "브라운"))
        communityDatas!!.add(CommunityData(R.drawable.cony, "two", "투", "코니"))

        CommonData.communityDatas = communityDatas!!

        adapter = CommunityAdapter(communityDatas)
        adapter!!.setOnItemClickListener(this)
        communityList!!.adapter = adapter

       community_write!!.setOnClickListener {
            val intent = Intent(activity, CommunityWriteActivity::class.java)
            startActivity(intent)
        }




//        val listener = object : OnMenuItemClickListener() {
//
//
//            fun onMenuItemClick(item: MenuItem): Boolean {
//
//                // TODO Auto-generated method stub
//
//
//                when (item.getItemId()) {
//                //눌러진 MenuItem의 Item Id를 얻어와 식별
//
//                    R.id.save ->
//                        Toast.makeText(this@MainActivity, "SAVE", Toast.LENGTH_SHORT).show()
//                    R.id.search ->
//                        Toast.makeText(this@MainActivity, "SEARCH", Toast.LENGTH_SHORT).show()
//                    R.id.setting ->
//                        Toast.makeText(this@MainActivity, "SETTING", Toast.LENGTH_SHORT).show()
//                }
//                return false
//            }
//       }

        return v


    }



    override fun onClick(p0: View?) {
        val fm = activity.fragmentManager
        val transacton = fm.beginTransaction()
        val fragment = CommunityDetailFragment()
        val bundle = Bundle()
        bundle.putInt("index", communityList!!.getChildAdapterPosition(p0!!))
        fragment.arguments = bundle

        transacton.add(R.id.community_container, fragment, "detial")
        transacton.addToBackStack(null)
        transacton.commit()




        //startActivity(Intent(activity,CommunityDetailActivity::class.java))
    }
//    private var clickListener = View.OnClickListener { v ->
//        when (v.id) {
//
//            R.id.community_write -> {
//                var intent = Intent(getActivity(), CommunityWriteActivity::class.java)
//                startActivity(intent)
//            }
//        }
//    }


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_community)

        communityList = view.findViewById(R.id.main_list)
        communityList!!.layoutManager = LinearLayoutManager(this)

        communityDatas = ArrayList<CommunityData>()
        communityDatas!!.add(CommunityData(R.drawable.brown, "one", "원", "브라운"))
        communityDatas!!.add(CommunityData(R.drawable.cony, "two", "투", "코니"))
        communityDatas!!.add(CommunityData(R.drawable.selly, "three", "삼", "샐리"))

        adapter = CommunityAdapter(communityDatas)
        adapter!!.setOnItenClickListener(this)
        communityList!!.adapter = adapter
    }*/

}

