package com.theshine.android.lites.data.remote.model.response

data class CommentResponse(
    val commentToken: String,
    val name : String,
    val profileImg : String?,
    val comment : String,
    val createdAt : String,
    val petToken : String,
    val userToken : String,
    val isBlocked : Int
)
