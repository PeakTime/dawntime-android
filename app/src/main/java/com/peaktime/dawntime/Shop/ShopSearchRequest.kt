package com.peaktime.dawntime.Shop

/**
 * Created by xlsdn on 2018-01-09.
 */
data class ShopSearchRequest(
        var min_price : Int,
        var max_price : Int,
        var goods_keyword : String
)