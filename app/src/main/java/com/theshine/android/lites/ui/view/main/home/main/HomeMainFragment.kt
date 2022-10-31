package com.theshine.android.lites.ui.view.main.home.main

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeMainBinding
import com.theshine.android.lites.databinding.FragmentSharedInfoListBinding
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListViewModel

class HomeMainFragment: BaseVmFragment<FragmentHomeMainBinding>(
    R.layout.fragment_home_main,
    HomeMainViewModel::class.java
) {
    override val viewModel by lazy { vm as HomeMainViewModel }

    override fun initFragment() {

    }
}