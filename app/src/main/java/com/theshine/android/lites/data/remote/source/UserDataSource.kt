package com.theshine.android.lites.data.remote.source

import com.theshine.android.lites.data.remote.api.UserApi
import com.theshine.android.lites.data.remote.model.VoidResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDataSource(
    private val userApi: UserApi
) {


    fun updateFcmToken(
        fcmToken: String
    ): Single<VoidResponse> {
        return userApi.updateFcmToken(fcmToken)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun unblockUser(
        userToken: String
    ): Single<VoidResponse> {
        return userApi.unblockUser(userToken)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun blockUser(
        userToken: String
    ): Single<VoidResponse> {
        return userApi.blockUser(userToken)
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
    }


}