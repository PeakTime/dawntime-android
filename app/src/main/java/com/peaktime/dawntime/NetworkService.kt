package com.peaktime.dawntime

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
interface NetworkService {

    @GET("/message/list/{user_email}")
    fun getMessageBoxList(
            @Path("user_email") user_email: String)
            : Call<MessageBoxResponse>


}