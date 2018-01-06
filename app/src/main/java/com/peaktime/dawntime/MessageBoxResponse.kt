package com.peaktime.dawntime

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
data class MessageBoxResponse(

        var status: String,
        var message: String,
        var result: ArrayList<MessageBoxList>
)