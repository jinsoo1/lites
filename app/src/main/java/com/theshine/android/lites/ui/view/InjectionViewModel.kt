package com.theshine.android.lites.ui.view

import com.theshine.android.lites.ui.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}
