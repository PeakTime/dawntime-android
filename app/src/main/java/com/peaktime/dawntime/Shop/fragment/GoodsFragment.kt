package com.peaktime.dawntime.Shop.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.CommonData
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import com.peaktime.dawntime.Shop.*
import kotlinx.android.synthetic.main.activity_shop_detail.*
import kotlinx.android.synthetic.main.fragment_shop_goods.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by xlsdn on 2018-01-03.
 */
class GoodsFragment : Fragment() , View.OnClickListener {

    private  var shopList : RecyclerView?=null
    private var shopBestDatas : ArrayList<ShopBestData> ?= null
    private  var shopAdapter : ShopAdapter? = null
    private  var bestFlag : Int? = null

    var networkService: NetworkService? = null
    var requestManager: RequestManager? = null
    var index : Int ?= null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_shop_goods, container, false)


        bestFlag = arguments.getInt("bestFlag")

        Log.e("aaaaaa",bestFlag.toString())
        shopList = v.findViewById(R.id.shopList)
        shopList!!.layoutManager = GridLayoutManager(activity, 2)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        v.fab.setOnClickListener(clickListener)
        v.fab.attachToRecyclerView(shopList!!)


        if(bestFlag == CommonData.CALL_AT_HOME_TO_SHOP) {
            getShopBest()

        }else if(bestFlag == CommonData.CALL_AT_TAB_TO_SHOP){
            getShopNew()
        }


        return v
    }

    override fun onClick(v: View?) {

        var intent = Intent(activity, ShopDetailActivity::class.java)

        val position : Int = shopList!!.getChildAdapterPosition(v) //position 받아오
       // var view : View = shopList!!.getChildAt(position)// 기
        /*val name : String = shopDatas!!.get(idx).shopName //포지션에 위치하는 이름받아오기
        val price : String = shopDatas!!.get(idx).shopPrice*/
        intent.putExtra("bestFlag", bestFlag)
        intent.putExtra("position", position)
        intent.putExtra("Goods_Id",shopBestDatas!!.get(shopList!!.getChildAdapterPosition(v)).goods_id)
        CommonData.shopLikeSend = v!!.findViewById(R.id.shopLikeBtn)
//        intent.putExtra("select_view",view as Serializable)
//        val name = v!!.goods_name.text
//        val price = v!!.goods_price.text
//        intent.putExtra("name", name)
//        intent.putExtra("price", price)

        startActivityForResult(intent, 0)
    }

    fun getShopBest() {
        var getContentList = networkService!!.getShopBestList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)

        getContentList.enqueue(object : Callback<ShopBestResponse> {
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful get best list")) {

//                        Log.i("status", "success")
//                        Log.i("size: ", response.body().result.toString())
//                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_name)
//                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_price.toString())

                        shopBestDatas = response.body().result
                        CommonData.shopBestList = shopBestDatas!!
                        shopAdapter = ShopAdapter(shopBestDatas,requestManager, CommonData.CALL_AT_HOME_TO_SHOP)
                        shopAdapter!!.setOnItemClickListener(this@GoodsFragment)
                        shopList!!.adapter = shopAdapter
                        Log.v("godds1", "goods1")
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<ShopBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

    fun getShopNew()     {
        var getContentList = networkService!!.getShopNewList(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!)

        getContentList.enqueue(object : Callback<ShopBestResponse> {
            override fun onResponse(call: Call<ShopBestResponse>?, response: Response<ShopBestResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful get new list")) {

//                        Log.i("status", "success")
//                        Log.i("size: ", response.body().result.toString())
//                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_name)
//                        Log.i("sajldlkasjdkl",shopBestList!!.get(0).goods_price.toString())

                        shopBestDatas = response.body().result
                        shopAdapter = ShopAdapter(shopBestDatas,requestManager, CommonData.CALL_AT_TAB_TO_SHOP)
                        shopAdapter!!.setOnItemClickListener(this@GoodsFragment)
                        Log.v("godds2", "goods1")
                        shopList!!.adapter = shopAdapter
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<ShopBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

////    private void refresh(){
//        FragmentTransaction transaction = getFragmentManager().beginTransaction()
//        transaction.detach(this).attach(this)commit()
//    }

    fun putShopLike(goods_id : Int){
            var getContentList = networkService!!.putShopLike(SharedPreferInstance.getInstance(activity).getPreferString("TOKEN")!!,goods_id)

        getContentList.enqueue(object : Callback<ShopLikeResponse> {
            override fun onResponse(call: Call<ShopLikeResponse>?, response: Response<ShopLikeResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful regist basket")) {
                        //좋아요했을때
                        Log.i("status", "성공성공성공성공성공성공성공성공성공성공성공")

                        //ApplicationController.instance!!.makeToast("북마크 변경.")
//                            if(){
                        shopLikeDetailBtn!!.setBackgroundResource(R.drawable.view_heart_solid)
//                                Log.i("status", "바뀜바뀜바뀜바뀜바뀜바뀜바뀜바뀜바뀜바뀜")
//                                shopLike = 1
//                            }else if(shopLike == 1){

//                                shopLike = 0
//                            }
                        //shopLikeDetailBtn.invalidate()


                    }
                    else if(response.body().message.equals("successful delete basket"))
                    {
                        Log.i("qwe","ㅁ니아ㅓㅁ니ㅏ)")
                        //좋아요 취소했을때

                        shopLikeDetailBtn!!.setBackgroundResource(R.drawable.view_heart_line)
                        // shopLikeDetailBtn.invalidate()

                    }
                } else {
                    Log.i("status", "failfailfailfailfailfailfailfailfailfailfail")
//                    ApplicationController.instance!!.makeToast("북마크 실패.")
                }
            }
            override fun onFailure(call: Call<ShopLikeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })

    }

    private var clickListener = View.OnClickListener { v ->
        //버튼이벤트
        when (v!!.id) {

            R.id.fab -> {
                //Toast.makeText(activity, "눌렸다", Toast.LENGTH_SHORT).show()
                activity.finish()
            }
            R.id.shopLikeBtn -> {
                putShopLike(index!!)
                Log.i("누룸", "눌ㄹ림눌림")
            }
        } //버튼이벤트 end

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (bestFlag == CommonData.CALL_AT_HOME_TO_SHOP) {
                getShopBest()

            } else if (bestFlag == CommonData.CALL_AT_TAB_TO_SHOP) {
                getShopNew()
            }
        }
    }
}