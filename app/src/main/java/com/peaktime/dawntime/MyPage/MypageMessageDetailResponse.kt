package com.peaktime.dawntime.MyPage

/**
 * Created by LEESANGYUN on 2018-01-11.
 */
data class MypageMessageDetailResponse(

        var status: Boolean,
        var message: String,
        var result: ArrayList<MypageMessageDetailData>
)
