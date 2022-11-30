package com.theshine.android.lites.ui.view.main.community.sharedInfo

import android.util.Log
import androidx.lifecycle.Observer
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
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SharedInfoListFragment: BaseVmFragment<FragmentSharedInfoListBinding>(
    R.layout.fragment_shared_info_list,
    SharedInfoListViewModel::class.java
) {
    override val viewModel by lazy { vm as SharedInfoListViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()

    override fun initFragment() {

        viewModel.setObserves()


        binding.apply {
            rvSharedInfoList.apply {
                adapter = SharedInfoListAdapter(viewModel)
            }
        }

    }

    private fun SharedInfoListViewModel.setObserves(){
        action.observe(this@SharedInfoListFragment, EventObserver{
            startActivity(
                intentFor<SharedInfoActivity>(
                    "feedToken" to it.feedToken,
                    "postState" to "정보공유"
                )
            )
        })

    }

    override fun onResume() {
        super.onResume()

        activityViewModel.regionData.observe(this@SharedInfoListFragment, Observer{
            Log.d("FeedListFragmentT", it.toString())
            viewModel.getSharedFeed(it)
        })

    }
}
class SharedInfoListAdapter(vm: SharedInfoListViewModel) :
    BaseRecyclerAdapter<SharedInfoData, ItemCommunitySharedinfoListBinding>(
        R.layout.item_community_sharedinfo_list, vm
    )
