package com.theshine.android.lites.data.common.model

data class ChatMyFeed(
    val feedToken: String,
    val createdAt: String,
    val title: String,
    val contents: String,
    val likeCount: Int,
    val commentCount: Int,
    val viewCount: Int
)