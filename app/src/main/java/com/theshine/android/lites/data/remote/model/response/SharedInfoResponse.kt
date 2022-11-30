package com.theshine.android.lites.data.remote.model.response

data class SharedInfoResponse(
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