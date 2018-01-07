package com.peaktime.dawntime

/**
 * Created by LEESANGYUN on 2018-01-07.
 */
data class CommunityDetailResponse(

        var status: Boolean,
        var message: String,
        var boardRelsult: ArrayList<CommunityDetailData>,
        var comResult: ArrayList<CommunityDetailData2>
)