package com.theshine.android.lites.ui.view.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kakao.sdk.common.util.Utility
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityMainBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.util.KeepStateNavigator
import org.jetbrains.anko.intentFor
import java.security.MessageDigest

class MainActivity : BaseVmActivity<ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    override val viewModel by lazy { vm as MainViewModel }
    override val toolbarId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val keyHash = Utility.getKeyHash(this)//onCreate 안에 입력해주자
//        Log.e("해쉬", keyHash)

        getAppKeyHash()

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        val navController = navHostFragment.navController

        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, binding.navHost.id)
        navController.navigatorProvider.addNavigator(navigator)

        navController.setGraph(R.navigation.nav_graph)

        binding.navBottom.setupWithNavController(navController)
    }

    override fun initActivity() {

        getAppKeyHash()


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