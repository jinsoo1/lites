package com.theshine.android.lites.ui.view.main.myPage.activity

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SharedInfoMyFeed
import com.theshine.android.lites.databinding.FragmentActivitySharedinfoBinding
import com.theshine.android.lites.databinding.ItemMypageSharedinfoListBinding

class ActivitySharedInfoFragment : BaseVmFragment<FragmentActivitySharedinfoBinding>(
    R.layout.fragment_activity_sharedinfo,
    ActivitySharedInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as ActivitySharedInfoViewModel }

    override fun initFragment() {

        //viewModel.setObserves()

    }

}

class ActivitySharedInfoAdapter(vm: ActivitySharedInfoViewModel) : BaseRecyclerAdapter<SharedInfoMyFeed, ItemMypageSharedinfoListBinding>(
    R.layout.item_mypage_sharedinfo_list, vm
) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        (holder.binding as ItemMypageSharedinfoListBinding).apply {

        }
    }
}
