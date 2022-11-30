package com.theshine.android.lites.ui.view.main.myPage.inquiry

import androidx.lifecycle.Observer
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

    private val inquiryToken by lazy {
        intent.getStringExtra("inquiryToken")
    }

    override fun initActivity() {


        inquiryToken?.let { viewModel.settingInquiryToken(it) }
        viewModel.setObserves()

    }

    private fun InquiryDetailViewModel.setObserves(){
        inquiryToken.observe(this@InquiryDetailActivity, Observer {
            getInquiryDetail()
        })
    }

}