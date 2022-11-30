package com.theshine.android.lites.data.remote.model.response

data class ProfileResponse(
    val petToken : String,
    val profileImg : String?,
    val name : String,
    val birth : String,
    val gender : Int,
    val weight : String?,
    val variety : String,
    val userToken :String,
    val isBlocked : Int
)
