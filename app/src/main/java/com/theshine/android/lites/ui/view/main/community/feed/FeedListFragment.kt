package com.theshine.android.lites.ui.view.main.community.feed

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.ImageFeed
import com.theshine.android.lites.databinding.FragmentFeedListBinding
import com.theshine.android.lites.databinding.FragmentSharedInfoListBinding
import com.theshine.android.lites.databinding.ItemImageFeedListBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.community.CommunityViewModel
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoActivity
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListViewModel
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FeedListFragment: BaseVmFragment<FragmentFeedListBinding>(
    R.layout.fragment_feed_list,
    FeedListViewModel::class.java
) {
    override val viewModel by lazy { vm as FeedListViewModel }

    val activityViewModel by sharedViewModel<MainViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

        binding.apply {
            rvImageFeedList.apply {
                adapter = ImageFeedListAdapter(viewModel)
                layoutManager = GridLayoutManager(context, 3)
            }
        }

    }

    private fun FeedListViewModel.setObserves(){

        action.observe(this@FeedListFragment, EventObserver{
            startActivity(
                intentFor<SharedInfoActivity>(
                    "feedToken" to it.feedToken,
                    "postState" to it.category
                )
            )
        })

    }

    override fun onResume() {
        super.onResume()
        activityViewModel.regionData.observe(this@FeedListFragment, Observer{
            Log.d("FeedListFragmentT", it.toString())
            viewModel.getImageFeedItem(it)
        })
    }
}

class ImageFeedListAdapter(vm : FeedListViewModel) : BaseRecyclerAdapter<ImageFeed, ItemImageFeedListBinding>(
    R.layout.item_image_feed_list, vm
)