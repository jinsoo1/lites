package com.theshine.android.lites.data.remote.model.response

import com.theshine.android.lites.data.common.model.PetInfo
import com.theshine.android.lites.data.common.model.User

data class PetResponse(
    val petInfo: PetInfo,
    val accessToken: String
)
