package com.peaktime.dawntime.Shop

/**
 * Created by 예은 on 2018-01-08.
 */
data class ShopBestResponse(
        var status: Boolean,
        var message : String,
        var result : ArrayList<ShopBestData>?
)