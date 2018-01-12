package com.peaktime.dawntime

import android.widget.ImageView
import com.peaktime.dawntime.Community.CommunityList
import com.peaktime.dawntime.MyPage.ChildMyPageMessageBoxData
import com.peaktime.dawntime.MyPage.MypageMycommentData
import com.peaktime.dawntime.Shop.ShopBestData
import com.peaktime.dawntime.Shop.ShopDetailData
import com.peaktime.dawntime.Shop.ShopKeywordData

/**
 * Created by HYEON on 2018-01-03.
 */
object CommonData {
    var communityDatas: ArrayList<CommunityList>? = null
    var messageBoxData: ArrayList<ChildMyPageMessageBoxData> = ArrayList()
    var mycommentData : ArrayList<MypageMycommentData>? = null
    var shopBestList :  ArrayList<ShopBestData> = ArrayList()
    var shopKeywordList : ArrayList<ShopKeywordData> = ArrayList()
    var shopDetailList :  ArrayList<ShopDetailData> = ArrayList()
    var shopLikeSend : ImageView? = null
    val CALL_AT_CATEGORY = 0
    val CALL_AT_BRAND = 1
    val CALL_AT_SEARCH = 2
    val CALL_AT_HOME_TO_SHOP = 0
    val CALL_AT_TAB_TO_SHOP = 1

    var lockFlag = 1
}