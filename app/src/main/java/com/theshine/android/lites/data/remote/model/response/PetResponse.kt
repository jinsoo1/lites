package com.theshine.android.lites.data.remote.model.response

data class  PetResponse(
    val petToken: String,
    val type : String,
    val name: String,
    val birth: String,
    val variety: String,
    val gender: Int,
    val neutering: Int,
    val moisture: String?,
    val protein: String?,
    val fat: String?,
    val fiber: String?,
    val ash: String?,
    val bcs: Int,
    val profileImg: String?
)
