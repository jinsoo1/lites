package com.theshine.android.lites.ui.view.main.home.graph

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentGraphBinding
import com.theshine.android.lites.databinding.FragmentHomeMainBinding
import com.theshine.android.lites.ui.view.main.home.main.HomeMainViewModel

class GraphFramgnet: BaseVmFragment<FragmentGraphBinding>(
    R.layout.fragment_graph,
    GraphViewModel::class.java
) {
    override val viewModel by lazy { vm as GraphViewModel }

    override fun initFragment() {

    }
}