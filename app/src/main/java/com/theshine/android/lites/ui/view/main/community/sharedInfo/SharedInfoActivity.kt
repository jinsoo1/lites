package com.theshine.android.lites.ui.view.main.community.sharedInfo

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.data.common.model.CommentList
import com.theshine.android.lites.databinding.ActivitySharedInfoBinding
import com.theshine.android.lites.databinding.ItemCommentListBinding

class SharedInfoActivity: BaseVmActivity<ActivitySharedInfoBinding>(
    R.layout.activity_shared_info,
    SharedInfoViewModel::class.java
) {

    override val viewModel by lazy { vm as SharedInfoViewModel }
    override val toolbarId = 0

    override fun initActivity() {

        binding.apply {
            rvComments.apply {
                adapter = CommentAdapter(viewModel)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

}

class CommentAdapter(vm: SharedInfoViewModel) :
        BaseRecyclerAdapter<CommentList, ItemCommentListBinding>(
            R.layout.item_comment_list, vm
        )