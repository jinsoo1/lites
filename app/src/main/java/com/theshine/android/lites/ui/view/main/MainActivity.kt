package com.theshine.android.lites.ui.view.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kakao.sdk.common.util.Utility
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityMainBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.ui.view.main.home.HomeFragment
import com.theshine.android.lites.ui.view.main.home.bluetooth.BleRepository
import com.theshine.android.lites.ui.view.main.home.main.HomeMainFragment
import com.theshine.android.lites.util.EventObserver
import com.theshine.android.lites.util.KeepStateNavigator
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject
import java.security.MessageDigest

class MainActivity : BaseVmActivity<ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    override val viewModel by lazy { vm as MainViewModel }
    override val toolbarId = 0

    val bleRepository : BleRepository by inject()

    lateinit var navHostFragment : NavHostFragment
    lateinit var navController : NavController

    override fun initActivity() {

        navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        navController = navHostFragment.navController

        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, binding.navHost.id)
        navController.navigatorProvider.addNavigator(navigator)

        navController.setGraph(R.navigation.nav_graph)

        binding.navBottom.setupWithNavController(navController)
        binding.navBottom.itemIconTintList = null
        Log.d("onCreate()", "activity")
        viewModel.setObserves()
    }

    private fun MainViewModel.setObserves(){

        action.observe(this@MainActivity, EventObserver{
            when(it){
                MainViewModel.MainAction.FIRST_POSITION ->{
                    binding.navBottom.menu.getItem(0).isChecked = true
                    navController.navigate(R.id.action_global_home_fragment)
                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()

        bleRepository.disconnectGattServer()
    }

    private fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.d("해시값", something)
            }
        } catch (e: Exception) {

            Log.e("name not found", e.toString())
        }
    }
}