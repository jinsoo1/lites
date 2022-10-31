package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.AuthApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import com.theshine.android.lites.data.remote.model.response.UserResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthDataSource(
    private val authApi: AuthApi
) {

    fun validateAccessToken(): Single<VoidResponse> {
        return authApi.validateAccessToken()
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginByGoogle(
        googleUserKey: String,
        email: String
    ): Single<UserResponse> {
        return authApi.loginByGoogle(googleUserKey, email)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginByKaKao(
        kakaoUserKey : String,
        email : String
    ) : Single<UserResponse>{
        return authApi.loginByKakao(kakaoUserKey, email)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun compareVersion(version : Double) : Single<VoidResponse>{
        return authApi.compareVersion(version)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }
}