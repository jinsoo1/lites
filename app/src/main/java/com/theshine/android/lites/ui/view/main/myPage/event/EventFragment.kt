package com.theshine.android.lites.ui.view.main.myPage.event

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageEventBinding
import com.theshine.android.lites.databinding.FragmentMypageManagementBinding
import com.theshine.android.lites.ui.view.main.myPage.management.ManagementViewModel

class EventFragment : BaseVmFragment<FragmentMypageEventBinding>(
    R.layout.fragment_mypage_event,
    EventViewModel::class.java
) {
    override val viewModel by lazy { vm as EventViewModel }

    override fun initFragment() {

    }
}