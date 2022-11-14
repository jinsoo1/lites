package com.theshine.android.lites.data.common.model

data class PetWeight(
    val petToken : String,
    val weight : Double,
    val createdAt : String,
    val name : String,
    val bcs : Int,
    val type : String,
    val moisture : String?
)
