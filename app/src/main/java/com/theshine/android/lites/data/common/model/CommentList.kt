package com.theshine.android.lites.data.common.model

import androidx.databinding.ObservableBoolean

data class CommentList(
    val commentToken: String,
    val userName: String,
    val profileImg : String?,
    val createdAt: String,
    val comment: String,
    val petToken : String,
    val userToken : String
){
    val isWriter : ObservableBoolean = ObservableBoolean(false)
    val isBlocked : ObservableBoolean = ObservableBoolean(false)
}