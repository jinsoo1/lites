package com.theshine.android.lites.data.remote.model.response

data class ProfileListResponse(
    val petToken: String,
    val name: String,
    val gender: Int,
    val birth: String,
    val birth2: String, //만나이
    val variety: String,
    val profileImage: String?,
    val weight: String?,
    val bcs: Int,
    val neutralization: Int
  )