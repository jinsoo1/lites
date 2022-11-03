package com.theshine.android.lites.data.remote

import com.theshine.android.lites.data.remote.source.AuthDataSource
import com.theshine.android.lites.data.remote.source.PetDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {


        //DataSource추가될때마다 한줄씩 추가
        single { AuthDataSource(get())}
        single { PetDataSource(get()) }
}