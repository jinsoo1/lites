package com.theshine.android.lites.ui.view.info

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.kakao.sdk.common.util.Utility
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityInfoBinding
import com.theshine.android.lites.databinding.ActivityLoginBinding
import com.theshine.android.lites.ui.view.login.LoginViewModel
import com.theshine.android.lites.util.KeepStateNavigator

class InfoActivity: BaseVmActivity<ActivityInfoBinding>(
    R.layout.activity_info,
    InfoViewModel::class.java
) {

    override val viewModel by lazy { vm as InfoViewModel }
    override val toolbarId = 0


    override fun initActivity() {

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostInfo.id) as NavHostFragment
        val navController = navHostFragment.navController

        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, binding.navHostInfo.id)
        navController.navigatorProvider.addNavigator(navigator)

        navController.setGraph(R.navigation.nav_graph_info)

    }

}