package com.peaktime.dawntime.Community

/**
 * Created by LEESANGYUN on 2018-01-11.
 */
data class TagSearchResponse(
        var status: Boolean,
        var message: String,
        var result: ArrayList<CommunityList>
)