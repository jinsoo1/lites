package com.theshine.android.lites.ui.view.main.myPage.management

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageManagementBinding
import com.theshine.android.lites.databinding.FragmentMypageNoticeBinding
import com.theshine.android.lites.ui.view.main.myPage.notice.NoticeViewModel

class ManagementFragment : BaseVmFragment<FragmentMypageManagementBinding>(
    R.layout.fragment_mypage_management,
    ManagementViewModel::class.java
) {
    override val viewModel by lazy { vm as ManagementViewModel }

    override fun initFragment() {

    }
}