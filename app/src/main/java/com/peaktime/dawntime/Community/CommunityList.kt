package com.peaktime.dawntime.Community

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
data class CommunityList(
        var board_id: Int,
        var board_title: String,
        var board_tag: String,
        var board_content: String,
        var board_image: String,
        var board_like: Int?,
        var com_count: Int?,
        var scrap_count: Int?,
        var user_like: Boolean,
        var user_scrap: Boolean

)
