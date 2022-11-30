package com.theshine.android.lites.ui.view.login.test

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityTestEmailBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.ui.view.main.MainActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.intentFor

class TestEmailActivity : BaseVmActivity<ActivityTestEmailBinding>(
    R.layout.activity_test_email,
    TestEmailViewModel::class.java
){
    override val viewModel by lazy { vm as TestEmailViewModel }
    override val toolbarId = 0

    override fun initActivity() {


        viewModel.setObserves()
    }


    private fun TestEmailViewModel.setObserves(){
        action.observe(this@TestEmailActivity, EventObserver{
            when(it){
                TestEmailViewModel.TestLoginActions.MAIN ->{
                    //로그인성공
                    startActivity(
                        intentFor<MainActivity>()
                    )
                    finish()
                }
                TestEmailViewModel.TestLoginActions.PET ->{
                    startActivity(
                        intentFor<InfoActivity>()
                    )
                    finish()
                }
            }
        })
    }

}