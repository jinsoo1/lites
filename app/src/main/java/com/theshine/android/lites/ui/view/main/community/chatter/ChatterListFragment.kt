package com.theshine.android.lites.ui.view.main.community.chatter

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentChatterListBinding
import com.theshine.android.lites.databinding.FragmentFeedListBinding
import com.theshine.android.lites.ui.view.main.community.feed.FeedListViewModel

class ChatterListFragment : BaseVmFragment<FragmentChatterListBinding>(
    R.layout.fragment_chatter_list,
    ChatterListViewModel::class.java
) {
    override val viewModel by lazy { vm as ChatterListViewModel }

    override fun initFragment() {

    }
}