package com.peaktime.dawntime.Home

import android.graphics.drawable.Drawable

/**
 * Created by minhyoung on 2018. 1. 2..
 */
data class PeektimeData (
        var peektimeBackground : Int,
        var peektimeTitle : String,
        var peektimeText : String,
        var peektimeLikeCnt : Int,
        var peektimeCommentCnt : Int,
        var peektimeScrapCnt : Int
)