package com.theshine.android.lites.ui.view.main.myPage.event

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.ActivityEventBinding


class EventActivity : BaseVmFragment<ActivityEventBinding>(
    R.layout.activity_event,
    EventViewModels::class.java
) {
    override val viewModel by lazy { vm as EventViewModels }

    override fun initFragment() {

    }
}