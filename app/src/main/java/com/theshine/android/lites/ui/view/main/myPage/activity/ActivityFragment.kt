package com.theshine.android.lites.ui.view.main.myPage.activity

import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoVarietyBinding
import com.theshine.android.lites.databinding.FragmentMypageActivityBinding
import com.theshine.android.lites.ui.view.info.bcs.BcsFragmentDirections
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.main.community.chatter.ChatterListFragment
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListFragment

class ActivityFragment : BaseVmFragment<FragmentMypageActivityBinding>(
    R.layout.fragment_mypage_activity,
    ActivityViewModel::class.java
) {
    override val viewModel by lazy { vm as ActivityViewModel }

    lateinit var tab1: ActivitySharedInfoFragment
    lateinit var tab2: ActivityChatFragment

    override fun initFragment() {

        tab1 = ActivitySharedInfoFragment()
        tab2 = ActivityChatFragment()

        binding.btnBack.setOnClickListener{
            val action = ActivityFragmentDirections.actionActivityFragmentToMyPageFragment()
            findNavController().navigate(action)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        //Tab1
                        Log.d("탭테스트", "탭1")
                        showTab1()

                    }
                    1 -> {
                        //Tab2
                        Log.d("탭테스트", "탭2")
                        showTab2()

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        //viewModel.setObserves()

    }

    fun showTab1(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_my_activity, tab1)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    fun showTab2(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_my_activity, tab2)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

}