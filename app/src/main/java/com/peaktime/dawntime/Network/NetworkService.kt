package com.peaktime.dawntime.Network

import com.peaktime.dawntime.Community.CommunityDetailInstance
import com.peaktime.dawntime.Community.CommunityDetailResponse
import com.peaktime.dawntime.Community.CommunityResponse
import com.peaktime.dawntime.MyPage.MessageBoxResponse
import com.peaktime.dawntime.Shop.*
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
    @GET("shop/best")
    fun getShopBestList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>

    //쇼핑몰 best 리스트 조회
    @GET("shop/New")
    fun getShopNewList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>
    //쇼핑몰 category 리스트 조회
    @GET("shop/category/{goods_category}/{order}")
    fun getShopCategoryList(
            @Header("user_token") user_token: String,
            @Path("goods_category") goods_category:String,
            @Path("order") order: Int)
            : Call<ShopBestResponse>
    //쇼핑몰 brand 리스트 조회
    @GET("shop/category/{goods_brand}/{order}")
    fun getShopBrandList(
            @Header("user_token") user_token: String,
            @Path("goods_brand") goods_brand:String,
            @Path("order") order: Int)
            : Call<ShopBestResponse>



    //인기검색어, 최근검색어불러오기
    @GET("shop/keyword")
    fun getKeywordList(
            @Header("user_token") user_token: String)
            : Call<ShopKeywordResponse>

    //최근검색어삭제
    @DELETE("shop/keyword/delete/{user_keyword}")
    fun deleteKeyword(
            @Header("user_token") user_token: String,
            @Path("user_keyword") user_keyword: String
    ) : Call<KeywordDeleteResponse>


    //샵 상품검색
    @POST("shop/search/{order}")
    fun shopSearch(
            @Header("user_token") user_token: String,
            @Path("order") order: Int,
            @Body shopSearchRequest : ShopSearchRequest
    ) : Call<ShopSearchResponse>

    //쇼핑몰 detail 상품 상세 조회
    @GET("shop/detail/{goods_id}")
    fun getShopDetailList(
            @Header("user_token") user_token:String,
            @Path("goods_id") goods_id: Int)
            : Call<ShopDetailResponse>


}

