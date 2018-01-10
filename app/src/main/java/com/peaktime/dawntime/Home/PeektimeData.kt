package com.peaktime.dawntime.Home

import android.graphics.drawable.Drawable

/**
 * Created by minhyoung on 2018. 1. 2..
 */
data class PeektimeData (
        var board_id : Int,
        var board_title : String,
        var board_tag : String,
        var board_content : String,
        var board_like : Int,
        var com_count : Int,
        var scrap_count : Int,
        var user_like : Boolean,
        var user_scrap : Boolean
)