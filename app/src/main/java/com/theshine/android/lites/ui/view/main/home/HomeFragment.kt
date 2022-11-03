package com.theshine.android.lites.ui.view.main.home

import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeBinding
import com.theshine.android.lites.ui.view.login.LoginActivity
import com.theshine.android.lites.ui.view.main.home.graph.GraphFramgnet
import com.theshine.android.lites.ui.view.main.home.main.HomeMainFragment
import com.theshine.android.lites.ui.view.main.home.weightinfo.WeightInfoFramgnet
import org.jetbrains.anko.support.v4.intentFor

class HomeFragment : BaseVmFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {
    override val viewModel by lazy { vm as HomeViewModel }

    lateinit var tab1: HomeMainFragment
    lateinit var tab2: WeightInfoFramgnet
    lateinit var tab3: GraphFramgnet

    override fun initFragment() {

        tab1 = HomeMainFragment()
        tab2 = WeightInfoFramgnet()
        tab3 = GraphFramgnet()
        showTab1()
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
                    2 -> {
                        Log.d("탭테스트", "탭3")
                        showTab3()

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    fun showTab1(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_home, tab1)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    fun showTab2(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_home, tab2)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    fun showTab3(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_home, tab3)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }
}