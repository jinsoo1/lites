package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.VoidResponse
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {



    @PATCH("/user/update/fcmtoken")
    @FormUrlEncoded
    fun updateFcmToken(
        @Field("fcmToken") fcmToken: String
    ): Single<VoidResponse>


    @HTTP(method = "DELETE", path = "/user/unblock", hasBody = true)
    @FormUrlEncoded
    fun unblockUser(
        @Field("userToken") userToken: String
    ): Single<VoidResponse>

    @POST("/user/block")
    @FormUrlEncoded
    fun blockUser(
        @Field("userToken") userToken: String
    ): Single<VoidResponse>
}