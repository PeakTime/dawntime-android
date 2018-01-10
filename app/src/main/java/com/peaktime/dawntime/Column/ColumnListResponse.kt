package com.peaktime.dawntime.Column

/**
 * Created by minhyoung on 2018. 1. 9..
 */
data class ColumnListResponse (
        var status : String,
        var result : ArrayList<ColumnListData>,
        var msg : String
)