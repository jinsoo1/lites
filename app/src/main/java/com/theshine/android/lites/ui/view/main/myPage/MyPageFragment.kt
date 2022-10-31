package com.theshine.android.lites.ui.view.main.myPage

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeBinding
import com.theshine.android.lites.databinding.FragmentMypageBinding
import com.theshine.android.lites.ui.view.main.home.HomeViewModel

class MyPageFragment : BaseVmFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
    MyPageViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageViewModel }

    override fun initFragment() {

    }
}