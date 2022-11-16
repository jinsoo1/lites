package com.theshine.android.lites.data.remote.api

import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.UserResponse
import io.reactivex.Single
import retrofit2.http.*

interface AuthApi {

    @GET("/auth/validate-access-token")
    fun validateAccessToken(): Single<VoidResponse>


    @POST("/auth/login/google")
    @FormUrlEncoded
    fun loginByGoogle(
        @Field("userToken") googleUserKey: String,
        @Field("email") email: String
    ): Single<DataResponse<UserResponse>>

    @POST("/auth/login/kakao")
    @FormUrlEncoded
    fun loginByKakao(
        @Field("userToken") kakaoUserKey: String,
        @Field("email") email: String
    ): Single<DataResponse<UserResponse>>

    @POST("/auth/compare")
    @FormUrlEncoded
    fun compareVersion(
        @Field("version") version : Double
    ) : Single<VoidResponse>
}