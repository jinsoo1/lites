package com.theshine.android.lites.data.common.model

import androidx.databinding.ObservableBoolean

data class SimpleSharedData(
    val feedToken : String,
    val title : String,
    val content : String,
    val createdAt : String,
    val Photo : String?,
    val likeCount : Int,
    val commentCount : Int,
    val visitCount : Int
){

    val activityState : ObservableBoolean = ObservableBoolean(false)

}
