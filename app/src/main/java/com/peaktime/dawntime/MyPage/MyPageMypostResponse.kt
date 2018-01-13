package com.peaktime.dawntime.MyPage

import com.peaktime.dawntime.Community.CommunityList

/**
 * Created by 예은 on 2018-01-11.
 */
data class MyPageMypostResponse (
        var status: Boolean,
        var message: String,
        var myPost_count: Int,
        var myCom_count: Int,
        var result : ArrayList<CommunityList>
)