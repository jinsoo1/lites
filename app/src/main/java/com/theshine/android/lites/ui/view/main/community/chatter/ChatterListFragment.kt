package com.theshine.android.lites.ui.view.main.community.chatter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SharedInfoData
import com.theshine.android.lites.databinding.FragmentChatterListBinding
import com.theshine.android.lites.databinding.ItemCommunityChatterListBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoActivity
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ChatterListFragment : BaseVmFragment<FragmentChatterListBinding>(
    R.layout.fragment_chatter_list,
    ChatterListViewModel::class.java
) {
    override val viewModel by lazy { vm as ChatterListViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()


    override fun initFragment() {

        viewModel.setObserves()
        binding.apply {
            rvChatterList.apply {
                adapter = ChatterListAdapter(viewModel)
            }
        }

    }

    private fun ChatterListViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            startActivity(
                intentFor<SharedInfoActivity>(
                    "feedToken" to it.feedToken,
                    "postState" to "수다방"
                )
            )
        })


    }

    override fun onResume() {
        super.onResume()

        activityViewModel.regionData.observe(this@ChatterListFragment, Observer{
            Log.d("FeedListFragmentO", it)
            viewModel.getChatterFeed(it)
        })
    }
}


class ChatterListAdapter(vm: ChatterListViewModel) :
    BaseRecyclerAdapter<SharedInfoData, ItemCommunityChatterListBinding>(
        R.layout.item_community_chatter_list, vm
    )
