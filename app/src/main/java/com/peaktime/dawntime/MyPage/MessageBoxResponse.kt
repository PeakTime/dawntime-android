package com.peaktime.dawntime.MyPage

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
data class MessageBoxResponse(

        var status: Boolean,
        var message: String,
        var result: ArrayList<MessageBoxList>
)