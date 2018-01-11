package com.peaktime.dawntime.Home

import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Column.ColumnFragment
import com.peaktime.dawntime.Column.ColumnListFragment
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Community.CommunityDetailFragment
import com.peaktime.dawntime.ExpandableHeightGridView
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.Shop.*
import com.peaktime.dawntime.Shop.ShopToMainActivity
import com.peaktime.dawntime.Shop.ShopActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),View.OnClickListener {

    private var shoplistRecycler : RecyclerView? = null
    private var shoplistDatas : ArrayList<ShoplistData>? = null
    private var shoplistAdapter : ShoplistAdapter? = null

    private var columnBannerScroll : ViewPager? = null
    private var columnBannerDatas : ArrayList<ColumnBannerData>? = null
    private var columnBannerAdapter : ColumnBannerAdapter? = null

    //private var peektimeRecycler :RecyclerView? = null
    private var peektimeDatas : ArrayList<PeektimeData>? = null
    //private var peektimeAdapter : PeektimeAdapter? = null

    private var peektimeGridAdapter : PeaktimeGridAdapter? = null
    private var peektimeGridView : ExpandableHeightGridView? = null

    private var timer : Timer? = null
    private val DELAY_MS : Long = 500
    private val PERIOD_MS : Long = 5000

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home,container,false)

        val peektimeBtn : View = v.findViewById(R.id.peektime_btn)
        val shopBtn : View = v.findViewById(R.id.shoplist_btn)
        val columnBtn : View = v.findViewById(R.id.column_btn)

        peektimeBtn.setOnClickListener(this)
        shopBtn.setOnClickListener(this)
        columnBtn.setOnClickListener(this)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        var mLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)
        mLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL

        shoplistRecycler = v.findViewById(R.id.shop_recycler_list)
        shoplistRecycler!!.layoutManager = mLayoutManager
        shoplistRecycler!!.addItemDecoration(RecyclerViewDecoration(16,"left"))

        columnBannerScroll = v.findViewById(R.id.column_viewpage)



        peektimeGridView = v.findViewById(R.id.peektime_grid_list)


        getHomeData()

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

    fun getHomeData(){
        val getContentList = networkService!!.getHome("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2VtYWlsIjoi7JiB66-866-86rK9IiwidXNlcl91aWQiOiIxMzIxMjEzMTMxIiwiaWF0IjoxNTE1NTczNTQwLCJleHAiOjE1MjQyMTM1NDB9.iazA1wUDy2wgeum1pNbc-LW3Qi2d2H_k-QVB3EjlBgxp1J7Z9_HhJwm6WZDCSaF6Tjijgbjz7eJQVeyVdCesqw")
        getContentList.enqueue(object : Callback<HomeResponse>{

            override fun onResponse(call: Call<HomeResponse>?, response: Response<HomeResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        shoplistDatas = response.body().main_shop
                        columnBannerDatas = response.body().main_column
                        peektimeDatas = response.body().main_peaktime

                        PeektimeObject!!.peektimeDatas = peektimeDatas

                        shoplistAdapter = ShoplistAdapter(shoplistDatas,requestManager!!,response.body().user_blind)
                        shoplistAdapter!!.setOnClickListener(View.OnClickListener { v: View? ->
                            var goods_id = shoplistDatas!!.get(shoplistRecycler!!.getChildLayoutPosition(v)).goods_id
                            Log.e("aaaaaa",goods_id.toString())
                            var intent = Intent(activity,ShopDetailActivity::class.java)
                            intent.putExtra("goods_id",goods_id)
                            startActivity(intent)
                        })

                        columnBannerAdapter = ColumnBannerAdapter(columnBannerDatas,requestManager!!)
                        columnBannerAdapter!!.SetOnItemClickListener(View.OnClickListener { v: View? ->
                            var column_id =
                                    columnBannerDatas!!.get((columnBannerScroll!!.currentItem % columnBannerDatas!!.size)).column_id


                            var bundle = Bundle()
                            bundle.putInt("column_id",column_id)

                            var fragment = ColumnFragment()
                            fragment.arguments = bundle

                            var fm = fragmentManager.beginTransaction()
                            fm.replace(R.id.home_fragment_container,fragment)
                            fm.addToBackStack(null)
                            fm.commit()

                        })

                        peektimeGridAdapter = PeaktimeGridAdapter(peektimeDatas)

                        shoplistRecycler!!.adapter = shoplistAdapter

                        columnBannerScroll!!.adapter = columnBannerAdapter
                        columnBannerScroll!!.setCurrentItem(shoplistDatas!!.size * 1000)

                        peektimeGridView!!.adapter = peektimeGridAdapter

                        peektimeGridView!!.setOnItemClickListener { parent, view, position, id ->
                            var board_id = peektimeDatas!!.get(position).board_id
                            var bundle = Bundle()
                            bundle.putInt("index",board_id)
                            var fragment = CommunityDetailFragment()
                            fragment.arguments = bundle

                            var fm = fragmentManager.beginTransaction()
                            fm.replace(R.id.home_fragment_container,fragment)
                            fm.addToBackStack(null)
                            fm.commit()
                        }
                        peektimeGridView!!.setExpanded(true)


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
        when(v!!.id){
            R.id.shoplist_btn->{
                var intent = Intent(activity, ShopToMainActivity::class.java)
                intent.putExtra("bestFlag", CommonData.CALL_AT_HOME_TO_SHOP)
                startActivity(intent)
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
