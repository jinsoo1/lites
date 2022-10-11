package com.theshine.android.lites.ui.view.main

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityMainBinding

class MainActivity : BaseVmActivity<ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {


    override val viewModel by lazy { vm as MainViewModel }
    override val toolbarId = 0

    override fun initActivity() {


    }
}