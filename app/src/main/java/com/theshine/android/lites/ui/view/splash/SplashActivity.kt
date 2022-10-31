package com.theshine.android.lites.ui.view.splash

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivitySplashBinding
import com.theshine.android.lites.ui.view.login.LoginActivity
import com.theshine.android.lites.ui.view.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.intentFor

class SplashActivity: BaseVmActivity<ActivitySplashBinding>(
    R.layout.activity_splash,
    SplashViewModel::class.java
) {

    override val viewModel by lazy { vm as SplashViewModel }
    override val toolbarId = 0


    override fun initActivity() {

        viewModel.setObserves()

    }

    fun SplashViewModel.setObserves(){

        action.observe(this@SplashActivity, Observer { it ->
            viewModelScope.launch {
                delay(1500)
                when(it){
                    SplashViewModel.SplashAction.MOVE_LOGIN ->{
                        startActivity(
                            intentFor<LoginActivity>()
                        )
                        finish()
                    }

                    SplashViewModel.SplashAction.MOVE_MAIN ->{
                        startActivity(
                            intentFor<MainActivity>()
                        )
                        finish()
                    }
                }
            }
        })
    }

}