package com.theshine.android.lites.data.common.model

data class PetData(
    val petToken: String,
    val petType : String,
    val name: String,
    val birth: String,
    val variety: String,
    val gender: Int,
    val neutralization: Int,
    val bcs: Int,
    val profileImg: String?,
    val moisture : String?,
    val protein : String?,
    val fat : String?,
    val fiber : String?,
    val ash : String?

)