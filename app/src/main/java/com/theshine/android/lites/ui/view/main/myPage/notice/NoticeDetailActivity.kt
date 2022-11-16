package com.theshine.android.lites.ui.view.main.myPage.notice

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityNoticeDetailBinding
import com.theshine.android.lites.databinding.ActivityProfileEditBinding
import com.theshine.android.lites.ui.view.main.myPage.profile.ProfileEditViewModel

class NoticeDetailActivity : BaseVmActivity<ActivityNoticeDetailBinding>(
    R.layout.activity_notice_detail,
    NoticeDetailViewModel::class.java
) {
    override val viewModel by lazy { vm as NoticeDetailViewModel }
    override val toolbarId = 0

    private val idx: Int by lazy { intent.getIntExtra("idx", 0) }
    private val title: String by lazy { intent.getStringExtra("title") ?:"" }
    private val content: String by lazy { intent.getStringExtra("content") ?:"" }
    private val createdAt: String by lazy { intent.getStringExtra("createdAt") ?:"" }

    override fun initActivity() {

        binding.tvTitle.text = title
        binding.tvDate.text = createdAt
        binding.tvDescription.text = content

    }
}