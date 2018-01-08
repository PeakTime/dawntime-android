package com.peaktime.dawntime.Network

import com.peaktime.dawntime.Community.CommunityDetailInstance
import com.peaktime.dawntime.Community.CommunityDetailResponse
import com.peaktime.dawntime.Community.CommunityResponse
import com.peaktime.dawntime.MyPage.MessageBoxResponse
import com.peaktime.dawntime.Shop.ShopBestResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
interface NetworkService {
    //쪽지함
    @GET("message/list/{user_email}")
    fun getMessageBoxList(
            @Path("user_email") user_email: String)
            : Call<MessageBoxResponse>

    //커뮤니티 리스트
    @FormUrlEncoded
    @POST("board/dateList")
    fun getCommunityList(@Field("user_id") user_id: Int)
            : Call<CommunityResponse>

    //커뮤니티 상세보기

    @POST("board/list/{board_id}")
    fun getCommunityDetail(
            @Path("board_id") board_id: Int,
            @Body instance: CommunityDetailInstance)
            : Call<CommunityDetailResponse>

    //쇼핑몰 best 리스트 조회
    @GET("/shop/best")
    fun getShopBestList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>


}