package com.theshine.android.lites.data.local.pref

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object PreferencesController : KoinComponent {
    val userInfoPref: BDPreferences.UserInfo by inject()
    val permanentPref: BDPreferences.Permanent by inject()
}