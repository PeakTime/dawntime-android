package com.peaktime.dawntime.Home

/**
 * Created by minhyoung on 2018. 1. 8..
 */
data class HomeResponse(
        var status : String,
        var user_blind : Int,
        var main_shop : ArrayList<ShoplistData>,
        var main_column : ArrayList<ColumnBannerData>,
        var main_peaktime : ArrayList<PeektimeData>,
        var msg : String
)