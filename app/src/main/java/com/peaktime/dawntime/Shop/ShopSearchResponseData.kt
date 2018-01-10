package com.peaktime.dawntime.Shop

/**
 * Created by xlsdn on 2018-01-09.
 */
data class ShopSearchResponseData (
        var goods_id : Int,
        var goods_name : String,
        var goods_brand : String,
        var goods_price : Int,
        var goods_info : String,
        var goods_image : String,
        var goods_url : String,
        var min_price : Int,
        var max_price : Int,
        var keyword : String,
        var goods_like : Int
)