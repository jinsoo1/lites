package com.theshine.android.lites.ui.view.main.myPage.management

import android.media.metrics.Event
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.local.UserLoginLocalDataSource
import com.theshine.android.lites.data.local.pref.PreferencesController
import com.theshine.android.lites.databinding.FragmentMypageManagementBinding
import com.theshine.android.lites.databinding.FragmentMypageNoticeBinding
import com.theshine.android.lites.ui.view.login.LoginActivity
import com.theshine.android.lites.ui.view.main.myPage.notice.NoticeViewModel
import com.theshine.android.lites.ui.view.splash.SplashActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.ext.android.inject

class ManagementFragment : BaseVmFragment<FragmentMypageManagementBinding>(
    R.layout.fragment_mypage_management,
    ManagementViewModel::class.java
) {
    override val viewModel by lazy { vm as ManagementViewModel }

    val localDataSource: UserLoginLocalDataSource by inject()

    override fun initFragment() {

        binding.tvEmail.text = PreferencesController.userInfoPref.email

        viewModel.setObserves()

    }

    fun ManagementViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                ManagementViewModel.ManagementActions.LOGOUT -> {
                        localDataSource.clearPref()
                        PreferencesController.userInfoPref.clearPref()
                        startActivity(
                            intentFor<LoginActivity>().newTask().clearTask()
                        )

                }
            }
        })
    }
}