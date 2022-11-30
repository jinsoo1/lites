package com.theshine.android.lites.ui.view.main.myPage.setting

import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageSettingBinding

class SettingFragment : BaseVmFragment<FragmentMypageSettingBinding>(
    R.layout.fragment_mypage_setting,
    SettingViewModel::class.java
) {
    override val viewModel by lazy { vm as SettingViewModel }

    override fun initFragment() {

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.setObserves()

    }

    fun SettingViewModel.setObserves(){
        isCommonAlertEnabled.observe(this@SettingFragment, Observer { viewModel.updateSetting() })
        isMarketingAlertEnabled.observe(this@SettingFragment, Observer { viewModel.updateSetting() })
    }
}