package com.theshine.android.lites.data.common.model

data class Profile(
    val petToken: String,
    val name: String,
    val gender: Int,
    val birth: String,
    val birth2 : String,
    val variety: String,
    val profileImage: String?,
    val weight: String?,
    val bcs: Int,
    val neutralization: Int
)