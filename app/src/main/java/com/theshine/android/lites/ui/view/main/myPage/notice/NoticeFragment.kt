package com.theshine.android.lites.ui.view.main.myPage.notice

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageNoticeBinding
import com.theshine.android.lites.databinding.FragmentMypageSettingBinding
import com.theshine.android.lites.ui.view.main.myPage.setting.SettingViewModel

class NoticeFragment : BaseVmFragment<FragmentMypageNoticeBinding>(
    R.layout.fragment_mypage_notice,
    NoticeViewModel::class.java
) {
    override val viewModel by lazy { vm as NoticeViewModel }

    override fun initFragment() {

    }
}