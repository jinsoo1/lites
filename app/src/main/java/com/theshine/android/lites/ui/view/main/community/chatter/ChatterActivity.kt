package com.theshine.android.lites.ui.view.main.community.chatter

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityChatterBinding
import com.theshine.android.lites.databinding.ActivityFeedBinding
import com.theshine.android.lites.ui.view.main.community.feed.FeedViewModel

class ChatterActivity: BaseVmActivity<ActivityChatterBinding>(
    R.layout.activity_chatter,
    ChatterViewModel::class.java
) {

    override val viewModel by lazy { vm as ChatterViewModel }
    override val toolbarId = 0

    override fun initActivity() {



    }

}