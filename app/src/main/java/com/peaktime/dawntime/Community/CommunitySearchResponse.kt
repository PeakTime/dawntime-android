package com.peaktime.dawntime.Community

/**
 * Created by LEESANGYUN on 2018-01-12.
 */
data class CommunitySearchResponse(
        var status: Boolean,
        var msg: String,
        var result: ArrayList<CommunityList>?
)