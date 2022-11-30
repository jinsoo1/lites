package com.theshine.android.lites.data.remote.model.response

data class SimpleSharedResponse(
    val feedToken : String,
    val title : String,
    val content : String,
    val createdAt : String,
    val Photo : String?,
    val likeCount : Int,
    val commentCount : Int,
    val visitCount : Int
)