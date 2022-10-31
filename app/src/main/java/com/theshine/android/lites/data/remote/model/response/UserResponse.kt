package com.theshine.android.lites.data.remote.model.response

import com.theshine.android.lites.data.common.model.User

data class UserResponse(
    val user: User,
    val accessToken: String,
    val refreshToken: String
)