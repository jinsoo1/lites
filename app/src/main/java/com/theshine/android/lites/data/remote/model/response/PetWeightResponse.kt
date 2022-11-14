package com.theshine.android.lites.data.remote.model.response

data class PetWeightResponse(
    val petToken : String,
    val weight : Double,
    val createdAt : String,
    val name : String,
    val bcs : Int,
    val type : String,
    val moisture : String?
)
