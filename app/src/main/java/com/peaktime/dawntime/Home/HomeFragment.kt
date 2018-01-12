package com.peaktime.dawntime.Home

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.*
import com.peaktime.dawntime.Column.ColumnFragment
import com.peaktime.dawntime.Column.ColumnListFragment
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.Shop.ShopDetailActivity
import com.peaktime.dawntime.Shop.ShopToMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.peaktime.dawntime.MainActivity
import android.view.KeyEvent.KEYCODE_BACK
import android.widget.Toast





class HomeFragment : Fragment(), View.OnClickListener {

    private var shoplistRecycler: RecyclerView? = null
    private var shoplistDatas: ArrayList<ShoplistData>? = null
    private var shoplistAdapter: ShoplistAdapter? = null

    private var columnBannerScroll: ViewPager? = null
    private var columnBannerDatas: ArrayList<ColumnBannerData>? = null
    private var columnBannerAdapter: ColumnBannerAdapter? = null

    //private var peektimeRecycler :RecyclerView? = null
    private var peektimeDatas: ArrayList<PeektimeData>? = null
    //private var peektimeAdapter : PeektimeAdapter? = null

    private var peektimeGridAdapter: PeaktimeGridAdapter? = null
    private var peektimeGridView: ExpandableHeightGridView? = null

    private var timer: Timer? = null
    private val DELAY_MS: Long = 500
    private val PERIOD_MS: Long = 5000

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var user_blind: Boolean? = null
    private var isLogin: Boolean? = null

    private var homeScroll : ScrollView? = null

    private val FINISH_INTERVAL_TIME : Long = 2000
    private var backPressedTime : Long = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

        val peektimeBtn: View = v.findViewById(R.id.peektime_btn)
        val shopBtn: View = v.findViewById(R.id.shoplist_btn)
        val columnBtn: View = v.findViewById(R.id.column_btn)
        homeScroll = v.findViewById(R.id.home_scrollview)

        peektimeBtn.setOnClickListener(this)
        shopBtn.setOnClickListener(this)
        columnBtn.setOnClickListener(this)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        var mLayoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        shoplistRecycler = v.findViewById(R.id.shop_recycler_list)
        shoplistRecycler!!.layoutManager = mLayoutManager
        shoplistRecycler!!.addItemDecoration(RecyclerViewDecoration(20, "left"))

        columnBannerScroll = v.findViewById(R.id.column_viewpage)

        peektimeGridView = v.findViewById(R.id.peektime_grid_list)

        isLogin = SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")

        if (SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")!!) {
            user_blind = SharedPreferInstance.getInstance(activity).getPreferBoolean("BLIND")
        } else {
            user_blind = true
        }

        getHomeData(user_blind!!)

        val handler = Handler()
        val Update = Runnable {
            /*            if (currentPage == NUM_PAGES - 1) {
                            currentPage = 0
                        }*/
            columnBannerScroll!!.setCurrentItem(columnBannerScroll!!.currentItem + 1, true)
        }

        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() { // task to be scheduled

            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

        return v

    }

    override fun onResume() {
        super.onResume()
        if(PeektimeObject.flag == 1) {
            var ft = fragmentManager.beginTransaction()
            ft.detach(this).attach(this).commit()
            PeektimeObject.flag = 0
        }
    }

    fun getHomeData(user_blind: Boolean) {


        val getContentList = networkService!!.getHome(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)
        getContentList.enqueue(object : Callback<HomeResponse> {

            override fun onResponse(call: Call<HomeResponse>?, response: Response<HomeResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().status.equals("success")) {
                        shoplistDatas = response.body().main_shop
                        columnBannerDatas = response.body().main_column
                        peektimeDatas = response.body().main_peaktime

                        PeektimeObject.peektimeDatas = peektimeDatas

                        shoplistAdapter = ShoplistAdapter(shoplistDatas, requestManager!!, user_blind)
                        shoplistAdapter!!.setOnClickListener(View.OnClickListener { v: View? ->
                            if(SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")!!) {
                                var goods_id = shoplistDatas!!.get(shoplistRecycler!!.getChildLayoutPosition(v)).goods_id
                                Log.e("aaaaaa", goods_id.toString())
                                var intent = Intent(activity, ShopDetailActivity::class.java)
                                intent.putExtra("Goods_Id", goods_id)
                                startActivity(intent)
                            }
                            else{
                                //로그인 팝업
                                startActivity(Intent(activity,LoginDialog::class.java))
                            }
                        })

                        columnBannerAdapter = ColumnBannerAdapter(columnBannerDatas, requestManager!!)
                        columnBannerAdapter!!.SetOnItemClickListener(View.OnClickListener { v: View? ->

                            var column_id =
                                    columnBannerDatas!!.get((columnBannerScroll!!.currentItem % columnBannerDatas!!.size)).column_id


                            var bundle = Bundle()
                            bundle.putInt("column_id", column_id)

                            var fragment = ColumnFragment()
                            fragment.arguments = bundle

                            var fm = fragmentManager.beginTransaction()
                            fm.replace(R.id.home_fragment_container, fragment)
                            fm.addToBackStack(null)
                            fm.commit()

                        })

                        peektimeGridAdapter = PeaktimeGridAdapter(peektimeDatas)

                        shoplistRecycler!!.adapter = shoplistAdapter

                        columnBannerScroll!!.adapter = columnBannerAdapter
                        columnBannerScroll!!.currentItem = shoplistDatas!!.size * 1000

                        peektimeGridView!!.adapter = peektimeGridAdapter

                        peektimeGridView!!.setOnItemClickListener { parent, view, position, id ->
                            if(SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")!!) {
                                var board_id = peektimeDatas!!.get(position).board_id
                                var bundle = Bundle()
                                bundle.putInt("index", board_id)
                                var fragment = CommunityDetailFragment()
                                fragment.arguments = bundle

                                var fm = fragmentManager.beginTransaction()
                                fm.replace(R.id.home_fragment_container, fragment)
                                fm.addToBackStack(null)
                                fm.commit()
                            }
                            else{
                                //로그인 팝업
                                startActivity(Intent(activity,LoginDialog::class.java))
                            }
                        }
                        peektimeGridView!!.setExpanded(true)
                        homeScroll!!.fullScroll(ScrollView.FOCUS_UP)


                    }
                } else {
                    Log.i("status", "fail")
                }

            }

            override fun onFailure(call: Call<HomeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.shoplist_btn -> {
                if (SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")!!) {
                    var intent = Intent(activity, ShopToMainActivity::class.java)
                    intent.putExtra("bestFlag", CommonData.CALL_AT_HOME_TO_SHOP)
                    startActivity(intent)
                } else {
                    //로그인 팝업
                    startActivity(Intent(activity,LoginDialog::class.java))
                }
            }
            R.id.column_btn -> {
                var childFm = fragmentManager.beginTransaction()
                var fragment: ColumnListFragment = ColumnListFragment()
                childFm!!.replace(R.id.home_fragment_container, fragment)
                childFm.addToBackStack(null)
                childFm.commit()
            }
            R.id.peektime_btn -> {
                if (SharedPreferInstance.getInstance(activity).getPreferBoolean("LOGIN")!!) {
                    var childFm = fragmentManager.beginTransaction()
                    PeektimeObject.peektimeDatas = peektimeDatas
                    val fragment = PeektimeFragment()
                    if (!fragment.isAdded) {
                        childFm!!.replace(R.id.home_fragment_container, fragment)
                        childFm.addToBackStack(null)
                        childFm.commit()
                    }
                }else {
                    //로그인 팝업
                    startActivity(Intent(activity,LoginDialog::class.java))
                }
            }
        }
    }

    class RecyclerViewDecoration(var div: Int?, var side: String) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            when (side) {
                "bottom" -> {
                    outRect!!.bottom = div!!
                }
                "left" -> {
                    outRect!!.left = div!!
                }
                "right" -> {
                    outRect!!.right = div!!
                }
            }

        }
    }


}
