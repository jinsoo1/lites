package com.theshine.android.lites.ui.view.main.myPage.activity

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentActivityChatBinding
import com.theshine.android.lites.databinding.FragmentMypageActivityBinding

class ActivityChatFragment: BaseVmFragment<FragmentActivityChatBinding>(
    R.layout.fragment_activity_chat,
    ActivityChatViewModel::class.java
) {
    override val viewModel by lazy { vm as ActivityChatViewModel }

    override fun initFragment() {

        //viewModel.setObserves()

    }

}