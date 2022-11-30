package com.theshine.android.lites.ui.view.main.myPage.inquiry

import com.theshine.android.lites.R
import com.theshine.android.lites.base.App
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.ActivityInquiryPostBinding
import com.theshine.android.lites.databinding.FragmentMypageInquiryBinding
import com.theshine.android.lites.util.EventObserver

class InquiryPostActivity : BaseVmActivity<ActivityInquiryPostBinding>(
    R.layout.activity_inquiry_post,
    InquiryPostViewModel::class.java
) {
    override val viewModel by lazy { vm as InquiryPostViewModel }
    override val toolbarId = 0

    override fun initActivity() {

        viewModel.setObserves()

    }

    fun InquiryPostViewModel.setObserves(){
        action.observe(lifecycleOwner, EventObserver{
            when(it){
                InquiryPostViewModel.InquiryPostActions.POST -> {
                    if (binding.etTitle.text.isNotEmpty()&&binding.etDescription.text.isNotEmpty()){
                        viewModel.postInquiryData()

                    } else{
                        App.toast("제목 또는 내용을 입력해주세요")

                    }
                }

                InquiryPostViewModel.InquiryPostActions.FINISH -> finish()
            }
        })
    }

}