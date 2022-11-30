package com.theshine.android.lites.data.common.model

import androidx.databinding.ObservableBoolean

data class ProfileData(
    val petToken : String,
    val profileImg : String?,
    val name : String,
    val birth : String,
    val gender : Boolean,
    val weight : String?,
    val variety : String,
    val userToken : String
){
    val isBlocked : ObservableBoolean = ObservableBoolean(false)
}