package com.theshine.android.lites.ui.view.activity.chatter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SimpleSharedData
import com.theshine.android.lites.databinding.FragmentUserChatterBinding
import com.theshine.android.lites.databinding.ItemUserfeedChatterBinding
import com.theshine.android.lites.ui.view.activity.ActivityUserViewModel
import com.theshine.android.lites.ui.view.activity.shared.UserFeedSharedListAdapter
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserChatterFragment : BaseVmFragment<FragmentUserChatterBinding>(
    R.layout.fragment_user_chatter,
    UserChatterViewModel::class.java
){
    override val viewModel by lazy { vm as UserChatterViewModel }
    val activityViewModel by sharedViewModel<ActivityUserViewModel>()

    override fun initFragment() {


        Log.d("onCreate()", "UserChatterFragment")
        activityViewModel.userToken.value?.let {

            viewModel.settingUserToken(it)
            Log.d("onCreate(Token)", it)
        }


        viewModel.setObserves()
        binding.apply {
            rvShared.apply {
                adapter = UserFeedChatterListAdapter(viewModel)
            }
        }
    }



    private fun UserChatterViewModel.setObserves(){
        userToken.observe(this@UserChatterFragment, Observer {
            Log.d("onCreate(Token2)", it)
            settingFeedState(false)
        })

        feedState.observe(this@UserChatterFragment, Observer {
            if(!it){
                viewModel.getUserChatterFeed()
            }else{
                //댓글조회
                getUserSharedComment()
            }

        })

        onClickItemData.observe(this@UserChatterFragment, EventObserver{
            startActivity(
                intentFor<SharedInfoActivity>(
                    "feedToken" to it.feedToken,
                    "postState" to "수다방"
                )
            )
        })
    }
}

class UserFeedChatterListAdapter(vm : UserChatterViewModel) : BaseRecyclerAdapter<SimpleSharedData, ItemUserfeedChatterBinding>(
    R.layout.item_userfeed_chatter, vm
)

