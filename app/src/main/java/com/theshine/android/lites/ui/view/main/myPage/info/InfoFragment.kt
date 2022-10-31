package com.theshine.android.lites.ui.view.main.myPage.info

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageInfoBinding

class InfoFragment : BaseVmFragment<FragmentMypageInfoBinding>(
    R.layout.fragment_mypage_info,
    MyPageInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageInfoViewModel }

    override fun initFragment() {

    }
}