package com.peaktime.dawntime.Home

import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.peaktime.dawntime.Column.ColumnFragment
import com.peaktime.dawntime.Column.ColumnListFragment
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.ExpandableHeightGridView
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.*
import com.peaktime.dawntime.Shop.ShopToMainActivity
import com.peaktime.dawntime.Shop.ShopActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),View.OnClickListener {

    private var shoplistRecycler : RecyclerView? = null
    private var shoplistDatas : ArrayList<ShoplistData>? = null
    private var shoplistAdapter : ShoplistAdapter? = null

    private var columnPageScroll : ViewPager? = null
    private var columnPageDatas : ArrayList<ColumnBannerData>? = null
    private var columnPageAdapter : ColumnBannerAdapter? = null

    //private var peektimeRecycler :RecyclerView? = null
    private var peektimeDatas : ArrayList<PeektimeData>? = null
    //private var peektimeAdapter : PeektimeAdapter? = null

    private var peektimeGridAdapter : PeaktimeGridAdapter? = null
    private var peektimeGridView : ExpandableHeightGridView? = null

    private var timer : Timer? = null
    private val DELAY_MS : Long = 500
    private val PERIOD_MS : Long = 5000


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home,container,false)

        val peektimeBtn : View = v.findViewById(R.id.peektime_btn)
        val shopBtn : View = v.findViewById(R.id.shoplist_btn)
        val columnBtn : View = v.findViewById(R.id.column_btn)
        peektimeBtn.setOnClickListener(this)
        shopBtn.setOnClickListener(this)
        columnBtn.setOnClickListener(this)

        var mLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)
        mLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL

        shoplistRecycler = v.findViewById(R.id.shop_recycler_list)
        shoplistRecycler!!.layoutManager = mLayoutManager
        shoplistRecycler!!.addItemDecoration(RecyclerViewDecoration(16,"left"))

        shoplistDatas = ArrayList<ShoplistData>()
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_black,"코니"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_green,"브라운"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_orange,"제이크"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_pink,"코니"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_purple,"브라운"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_green,"제이크"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_purple,"코니"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_orange,"브라운"))
        shoplistDatas!!.add(ShoplistData(R.drawable.view_blind_black,"제이크"))
        shoplistAdapter = ShoplistAdapter(shoplistDatas)

        shoplistRecycler!!.adapter = shoplistAdapter

      columnPageDatas = ArrayList<ColumnBannerData>()
        columnPageDatas!!.add(ColumnBannerData(R.drawable.cony))
        columnPageDatas!!.add(ColumnBannerData(R.drawable.brown))
        columnPageDatas!!.add(ColumnBannerData(R.drawable.jake))
        columnPageDatas!!.add(ColumnBannerData(R.drawable.fire))

        columnPageScroll = v.findViewById(R.id.column_viewpage)

        columnPageAdapter = ColumnBannerAdapter(columnPageDatas)
        columnPageAdapter!!.SetOnItemClickListener(View.OnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.home_fragment_container,ColumnFragment())
            fm.addToBackStack(null)
            fm.commit()
        })

        columnPageScroll!!.adapter = columnPageAdapter
        columnPageScroll!!.setCurrentItem(shoplistDatas!!.size * 1000)

        val handler = Handler()
        val Update = Runnable {
/*            if (currentPage == NUM_PAGES - 1) {
                currentPage = 0
            }*/
            columnPageScroll!!.setCurrentItem(columnPageScroll!!.currentItem + 1, true)
        }

        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() { // task to be scheduled

            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

//        mLayoutManager = GridLayoutManager(activity,2)
//        peektimeRecycler = v.findViewById(R.id.peektime_recycler_list)
//        peektimeRecycler!!.layoutManager = mLayoutManager

        peektimeDatas = ArrayList<PeektimeData>()
        var temp = "너무너무너무너무 긴 텍스트 입니다."
        var str : String? = null
        if(temp.length > 6){
            str = temp.substring(0,6)
            str = str + "..."
        }
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu1_purple,str!!,"qqq",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu2_green,"bbb","ccc",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu3_violet,"ccc","eee",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu4_blue,"abc","qqq",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu1_purple,"bbb","ccc",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu2_green,"ccc","eee",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu3_violet,"abc","qqq",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu4_blue,"bbb","ccc",300,200,100))
        peektimeDatas!!.add(PeektimeData(R.drawable.view_peakillu1_purple,"ccc","eee",300,200,100))

//        peektimeAdapter = PeektimeAdapter(peektimeDatas)
//        peektimeAdapter!!.setOnItemClick(View.OnClickListener {
//            val fm = fragmentManager.beginTransaction()
//            fm.replace(R.id.home_fragment_container,CommunityDetailFragment())
//            fm.addToBackStack(null)
//            fm.commit()
//        })

  //      peektimeRecycler!!.adapter = peektimeAdapter
  //      peektimeRecycler!!.addItemDecoration(RecyclerViewDecoration(15,"bottom"))


        peektimeGridAdapter = PeaktimeGridAdapter(peektimeDatas)
        peektimeGridAdapter!!.setOnItemClick(View.OnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.home_fragment_container,CommunityDetailFragment())
            fm.addToBackStack(null)
            fm.commit()
        })
        peektimeGridView = v.findViewById(R.id.peektime_grid_list)
        peektimeGridView!!.adapter = peektimeGridAdapter
        peektimeGridView!!.setExpanded(true)
//        v.peektime_grid_list.adapter = peektimeGridAdapter
//        v.peektime_grid_list.setExpanded(true)




        return v

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.shoplist_btn->{
                ShopToMainActivity.bestFlagFun.bestFlag = 1
                startActivity(Intent(activity, ShopToMainActivity::class.java))
            }
            R.id.column_btn->{
                var childFm = fragmentManager.beginTransaction()
                var fragment : ColumnListFragment = ColumnListFragment()
                childFm!!.replace(R.id.home_fragment_container,fragment)
                childFm!!.addToBackStack(null)
                childFm!!.commit()
            }
            R.id.peektime_btn->{
                var childFm = fragmentManager.beginTransaction()
                PeektimeObject.peektimeDatas = peektimeDatas
                val fragment = PeektimeFragment()
                if(!fragment.isAdded) {
                    childFm!!.replace(R.id.home_fragment_container, fragment)
                    childFm!!.addToBackStack(null)
                    childFm!!.commit()
                }
            }
        }
    }
    class RecyclerViewDecoration(var div : Int?,var side : String) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            when(side){
                "bottom"->{
                    outRect!!.bottom = div!!
                }
                "left"->{
                    outRect!!.left = div!!
                }
                "right"->{
                    outRect!!.right = div!!
                }
            }

        }
    }


}
