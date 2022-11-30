package com.theshine.android.lites.data.common.model

data class SharedInfoData(
    val feedToken : String,
    val title : String,
    val content : String,
    val createdAt : String,
    val name : String,
    val profileImg : String?,
    val Photo : String?,
    val likeCount : Int,
    val commentCount : Int,
    val visitCount : Int

    )