package com.theshine.android.lites.ui.view.main.home.weightinfo

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeMainBinding
import com.theshine.android.lites.databinding.FragmentWeightInfoBinding
import com.theshine.android.lites.ui.view.main.home.main.HomeMainViewModel

class WeightInfoFramgnet : BaseVmFragment<FragmentWeightInfoBinding>(
    R.layout.fragment_weight_info,
    WeightInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as WeightInfoViewModel }

    override fun initFragment() {

    }
}