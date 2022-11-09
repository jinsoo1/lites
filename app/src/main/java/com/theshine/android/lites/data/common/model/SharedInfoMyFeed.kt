package com.theshine.android.lites.data.common.model

import com.google.common.math.IntMath

data class SharedInfoMyFeed(
    val feedToken: String,
    val createdAt: String,
    val title: String,
    val contents: String,
    val likeCount: Int,
    val commentCount: Int,
    val viewCount: Int
)