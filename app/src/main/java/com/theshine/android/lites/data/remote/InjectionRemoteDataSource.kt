package com.theshine.android.lites.data.remote

import com.theshine.android.lites.data.remote.source.*
import org.koin.dsl.module

val remoteDataSourceModule = module {


        //DataSource추가될때마다 한줄씩 추가
        single { AuthDataSource(get())}
        single { PetDataSource(get()) }
        single { MyPageDataSource(get()) }
        single { FeedDataSource(get()) }
        single { UserDataSource(get()) }
        single { SettingDataSource(get()) }
}