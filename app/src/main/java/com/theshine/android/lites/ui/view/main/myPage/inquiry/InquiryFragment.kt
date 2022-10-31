package com.theshine.android.lites.ui.view.main.myPage.inquiry

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageInquiryBinding
import com.theshine.android.lites.databinding.FragmentMypageManagementBinding
import com.theshine.android.lites.ui.view.main.myPage.management.ManagementViewModel

class InquiryFragment : BaseVmFragment<FragmentMypageInquiryBinding>(
    R.layout.fragment_mypage_inquiry,
    InquiryViewModel::class.java
) {
    override val viewModel by lazy { vm as InquiryViewModel }

    override fun initFragment() {

    }
}