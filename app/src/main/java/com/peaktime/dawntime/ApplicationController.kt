package com.peaktime.dawntime

import android.app.Application
import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
class ApplicationController : Application() {
    var networkService: NetworkService? = null
        private set
    var baseUrl = "http://13.125.78.152:6789"
    var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()

        mContext = this

        buildNetwork()
    }

    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null

    }
}