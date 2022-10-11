package com.theshine.android.lites.util.ext

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun setupKoin(
    context: Context,
    vararg module: Module
) {
    startKoin {
        androidContext(context)
        modules(*module)
    }
}