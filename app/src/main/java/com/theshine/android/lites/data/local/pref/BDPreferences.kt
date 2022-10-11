package com.theshine.android.lites.data.local.pref

interface BDPreferences {
    interface UserInfo {
        var accessToken: String
        var refreshToken: String

        var userType: String
        var userToken: String
        var nickname: String
        var email: String
        var profileImg: String
        var bio: String


        var noticeCreated : String

        fun clearPref()
    }

    interface Permanent {
    }
}