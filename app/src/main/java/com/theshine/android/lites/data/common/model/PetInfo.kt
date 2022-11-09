package com.theshine.android.lites.data.common.model

data class PetInfo(
    val petToken: String,
    val petType : String,
    val name: String,
    val birth: String,
    val variety: String,
    val gender: Int,
    val neutralization: Int,
    val height: String,
    val waist: String,
    val bcs: Int,
    val profileImg: String?
)