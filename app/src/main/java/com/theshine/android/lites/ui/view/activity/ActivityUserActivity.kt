package com.theshine.android.lites.ui.view.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityUserActivityBinding
import com.theshine.android.lites.ui.view.activity.chatter.UserChatterFragment
import com.theshine.android.lites.ui.view.activity.shared.UserSharedFragment
import com.theshine.android.lites.util.FragmentUtils

class ActivityUserActivity : BaseVmActivity<ActivityUserActivityBinding>(
    R.layout.activity_user_activity,
    ActivityUserViewModel::class.java
){
    override val viewModel by lazy { vm as ActivityUserViewModel }
    override val toolbarId = 0

    private val userToken by lazy {
        intent.getStringExtra("userToken")
    }

    private val position by lazy {
        intent.getIntExtra("position", 0)
    }

    private val my by lazy {
        intent.getBooleanExtra("my", false)
    }


    private lateinit var userSharedFragment : UserSharedFragment
    private lateinit var userChatterFragment : UserChatterFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userSharedFragment = UserSharedFragment()
        userChatterFragment = UserChatterFragment()

        if(position == 0) {
            showTab1()
        } else{
            showTab2()
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1))
        }
    }

    override fun initActivity() {
        userToken?.let { viewModel.settingUserToken(it) }

        if(my){
            binding.tvTitle.text = "내 활동"
        }else{
            binding.tvTitle.text = "유저 활동"
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 ->  showTab1()
                    1 ->  showTab2()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.btnBack.setOnClickListener {
            finish()
        }


    }

    fun showTab1(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_my_activity, userSharedFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun showTab2(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_my_activity, userChatterFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }


}