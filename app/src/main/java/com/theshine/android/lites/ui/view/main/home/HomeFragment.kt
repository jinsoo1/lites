package com.theshine.android.lites.ui.view.main.home

import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.home.graph.GraphFragment
import com.theshine.android.lites.ui.view.main.home.main.HomeMainFragment
import com.theshine.android.lites.ui.view.main.home.weightinfo.WeightInfoFragment
import com.theshine.android.lites.util.FragmentUtils
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseVmFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {
    override val viewModel by lazy { vm as HomeViewModel }

    private val homeFragment : HomeMainFragment by lazy { HomeMainFragment() }
    private val weightInfoFragment : WeightInfoFragment by lazy { WeightInfoFragment() }
    private val graphFragment : GraphFragment by lazy { GraphFragment() }

    lateinit var fragments : FragmentUtils

    override fun initFragment() {
        Log.d("onCreate()", "fragment")


        fragments = FragmentUtils(
            R.id.frameLayout_home,
            childFragmentManager,
            arrayOf(
                homeFragment,
                weightInfoFragment,
                graphFragment
            )
        )
        switchPage(0)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> switchPage(0)
                    1 -> switchPage(1)
                    2 -> switchPage(2)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewModel.setObserves()

    }

    private fun HomeViewModel.setObserves(){
        myPet.observe(this@HomeFragment, Observer {
            Log.d("HomeFragment!! s", it.toString())
        })
    }

    fun switchPage(position: Int) {
        Log.d("onCreate()", "switchPage(")
        fragments.let{fragments.setCurrentFragmentByPosition(position)}

    }

}