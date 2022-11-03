package com.theshine.android.lites.data.local

import com.theshine.android.lites.data.local.pref.BDPreferences
import com.theshine.android.lites.data.local.pref.PermanentPreferences
import com.theshine.android.lites.data.local.pref.UserInfoPreferences
import org.koin.dsl.module

val localDataSourceModule = module {
    single<BDPreferences.UserInfo> { UserInfoPreferences(get()) }
    single<BDPreferences.Permanent> { PermanentPreferences(get()) }

    single<UserLoginLocalDataSource> { UserLoginLocalDataSource() }
}