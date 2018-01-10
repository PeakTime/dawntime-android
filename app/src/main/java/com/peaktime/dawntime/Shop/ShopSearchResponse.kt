package com.peaktime.dawntime.Shop

/**
 * Created by xlsdn on 2018-01-09.
 */
data class ShopSearchResponse (
        var status: Boolean,
        var message : String,
        var result : ArrayList<ShopSearchResponseData>
)