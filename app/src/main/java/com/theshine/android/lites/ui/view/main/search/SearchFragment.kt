package com.theshine.android.lites.ui.view.main.search

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageBinding
import com.theshine.android.lites.databinding.FragmentSearchBinding
import com.theshine.android.lites.ui.view.main.myPage.MyPageViewModel

class SearchFragment : BaseVmFragment<FragmentSearchBinding>(
    R.layout.fragment_search,
    SearchViewModel::class.java
) {
    override val viewModel by lazy { vm as SearchViewModel }

    override fun initFragment() {

    }
}