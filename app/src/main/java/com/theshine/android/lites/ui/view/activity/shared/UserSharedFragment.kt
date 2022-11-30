package com.theshine.android.lites.ui.view.activity.shared

import android.util.Log
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SimpleSharedData
import com.theshine.android.lites.databinding.FragmentUserSharedBinding
import com.theshine.android.lites.databinding.ItemUserfeedSharedBinding
import com.theshine.android.lites.ui.view.activity.ActivityUserViewModel
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserSharedFragment : BaseVmFragment<FragmentUserSharedBinding>(
    R.layout.fragment_user_shared,
    UserSharedViewModel::class.java
){
    override val viewModel by lazy { vm as UserSharedViewModel }

    val activityViewModel by sharedViewModel<ActivityUserViewModel>()
    override fun initFragment() {
        Log.d("onCreate()", "UserSharedFragment")
        activityViewModel.userToken.value?.let { viewModel.settingUserToken(it) }

        viewModel.setObserves()

        binding.apply {
            rvShared.apply {
                adapter = UserFeedSharedListAdapter(viewModel)
            }
        }

    }

    private fun UserSharedViewModel.setObserves(){
        userToken.observe(this@UserSharedFragment, Observer {
            settingFeedState(false)
        })

        feedState.observe(this@UserSharedFragment, Observer {
            if(!it){
                viewModel.getUserSharedFeed()
            }else{
                //댓글조회
                getUserSharedComment()
            }

        })

        onClickItemData.observe(this@UserSharedFragment, EventObserver{
            startActivity(
                intentFor<SharedInfoActivity>(
                    "feedToken" to it.feedToken,
                    "postState" to "정보공유"
                )
            )
        })
    }


}

class UserFeedSharedListAdapter(vm : UserSharedViewModel) : BaseRecyclerAdapter<SimpleSharedData, ItemUserfeedSharedBinding>(
    R.layout.item_userfeed_shared, vm
)