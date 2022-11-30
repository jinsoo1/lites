package com.theshine.android.lites.data.remote.model.response

data class DetailFeedResponse(
    val feedToken : String,
    val title : String,
    val content : String,
    val createdAt : String,
    val name : String,
    val profileImg : String?,
    val Photo : List<String>?,
    val petToken : String,
    val likeCount : Int,
    val commentCount : Int,
    val visitCount : Int,
    val userToken : String
)
