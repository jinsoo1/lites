package com.theshine.android.lites.data.local

import com.theshine.android.lites.data.common.model.User
import com.theshine.android.lites.data.local.pref.PreferencesController.userInfoPref

class UserLoginLocalDataSource {


    fun saveLoginInfo(
        user: User
    ) {
        userInfoPref.apply {
            userType = user.userType
            userToken = user.userToken
            nickname = user.name
            email = user.email
            agree = user.agree == 1
        }
    }


    fun saveAccessToken(accessToken: String){
        userInfoPref.accessToken = accessToken
    }

    fun saveRefreshToken(refreshToken: String){
        userInfoPref.refreshToken = refreshToken
    }

    fun clearPref() {
        userInfoPref.clearPref()
    }


}