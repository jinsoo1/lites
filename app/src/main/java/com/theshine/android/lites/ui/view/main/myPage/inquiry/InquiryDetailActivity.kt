package com.theshine.android.lites.ui.view.main.myPage.inquiry

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityInquiryDetailBinding
import com.theshine.android.lites.databinding.ActivityInquiryPostBinding

class InquiryDetailActivity: BaseVmActivity<ActivityInquiryDetailBinding>(
    R.layout.activity_inquiry_detail,
    InquiryDetailViewModel::class.java
) {
    override val viewModel by lazy { vm as InquiryDetailViewModel }
    override val toolbarId = 0

    override fun initActivity() {

    }


}