package com.peaktime.dawntime.MyPage

import com.peaktime.dawntime.Community.CommunityList

/**
 * Created by 예은 on 2018-01-12.
 */
data class MyPageMycommentResponse (
        var status : String,
        var message : String,
        var myPost_count : Int,
        var myCom_count : Int,
        var result : ArrayList<MypageMycommentData>
)