package com.theshine.android.lites.ui.view.main.community.feed

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityFeedBinding
import com.theshine.android.lites.databinding.ActivitySharedInfoBinding
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoViewModel

class FeedActivity: BaseVmActivity<ActivityFeedBinding>(
    R.layout.activity_feed,
    FeedViewModel::class.java
) {

    override val viewModel by lazy { vm as FeedViewModel }
    override val toolbarId = 0

    override fun initActivity() {



    }

}