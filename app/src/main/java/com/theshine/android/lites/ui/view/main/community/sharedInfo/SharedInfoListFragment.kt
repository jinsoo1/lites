package com.theshine.android.lites.ui.view.main.community.sharedInfo

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SharedInfoData
import com.theshine.android.lites.databinding.FragmentSharedInfoListBinding
import com.theshine.android.lites.databinding.ItemCommunitySharedinfoListBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.community.ReadyDialog
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SharedInfoListFragment: BaseVmFragment<FragmentSharedInfoListBinding>(
    R.layout.fragment_shared_info_list,
    SharedInfoListViewModel::class.java
) {
    override val viewModel by lazy { vm as SharedInfoListViewModel }


    override fun initFragment() {


        binding.apply {
            rvSharedInfoList.apply {
                adapter = SharedInfoListAdapter(viewModel)
                layoutManager = LinearLayoutManager(context)
            }
        }

    }
}
class SharedInfoListAdapter(vm: SharedInfoListViewModel) :
    BaseRecyclerAdapter<SharedInfoData, ItemCommunitySharedinfoListBinding>(
        R.layout.item_community_sharedinfo_list, vm
    )
