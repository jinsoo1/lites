package com.theshine.android.lites.ui.view.main.community.feed

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentFeedListBinding
import com.theshine.android.lites.databinding.FragmentSharedInfoListBinding
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListViewModel

class FeedListFragment: BaseVmFragment<FragmentFeedListBinding>(
    R.layout.fragment_feed_list,
    FeedListViewModel::class.java
) {
    override val viewModel by lazy { vm as FeedListViewModel }

    override fun initFragment() {

    }
}