package com.theshine.android.lites.data.common.model

data class CommentList(
    val commentToken: String,
    val userName: String,
    val writerState : Int,
    val createdAt: String,
    val comment: String,
)