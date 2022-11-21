package com.theshine.android.lites.ui.view.main.myPage.setting

import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeBinding
import com.theshine.android.lites.databinding.FragmentMypageSettingBinding
import com.theshine.android.lites.ui.view.main.home.HomeViewModel
import com.theshine.android.lites.ui.view.main.myPage.inquiry.InquiryFragmentDirections

class SettingFragment : BaseVmFragment<FragmentMypageSettingBinding>(
    R.layout.fragment_mypage_setting,
    SettingViewModel::class.java
) {
    override val viewModel by lazy { vm as SettingViewModel }

    override fun initFragment() {

        binding.btnBack.setOnClickListener {
            val action = InquiryFragmentDirections.actionInquiryFragmentToMyPageFragment()
            findNavController().navigate(action)
        }


    }
}