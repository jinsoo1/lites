package com.theshine.android.lites.data.remote.model.response

data class PetResponse(
    val petToken: String,
    val petType : String,
    val name: String,
    val birth: String,
    val variety: String,
    val gender: Int,
    val neutering: Int,
    val height: String,
    val waist: String,
    val bcs: Int,
    val profileImg: String
)
