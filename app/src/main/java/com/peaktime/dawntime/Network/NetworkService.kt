package com.peaktime.dawntime.Network

import com.peaktime.dawntime.Column.ColumnListResponse
import com.peaktime.dawntime.Column.ColumnResponse
import com.peaktime.dawntime.Community.CommunityDetailInstance
import com.peaktime.dawntime.Community.CommunityDetailResponse
import com.peaktime.dawntime.Community.CommunityResponse
import com.peaktime.dawntime.Home.HomeResponse
import com.peaktime.dawntime.MyPage.MessageBoxResponse
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

    //홈 화면
    @GET("home")
    fun getHome(@Header("user_token") user_token: String)
            : Call<HomeResponse>

    //칼럼 리스트
    @GET("column/list")
    fun getColumnList() : Call<ColumnListResponse>

    //칼럼 검색
    @FormUrlEncoded
    @POST("column/search")
    fun getColumnSearch(@Field("column_title") column_title : String)
                : Call<ColumnListResponse>


    //칼럼 상세보기

    @GET("column/detail/{column_id}")
    fun getColumn(
        @Path("column_id") column_id : Int)
            : Call<ColumnResponse>


}