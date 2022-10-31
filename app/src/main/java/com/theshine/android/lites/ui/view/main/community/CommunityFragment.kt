package com.theshine.android.lites.ui.view.main.community

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentCommunityBinding
import com.theshine.android.lites.databinding.FragmentSearchBinding
import com.theshine.android.lites.ui.view.main.community.chatter.ChatterListFragment
import com.theshine.android.lites.ui.view.main.community.feed.FeedListFragment
import com.theshine.android.lites.ui.view.main.community.post.PostWriteActivity
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListFragment
import com.theshine.android.lites.ui.view.main.search.SearchViewModel
import io.reactivex.internal.schedulers.SchedulerPoolFactory.start
import org.jetbrains.anko.support.v4.intentFor

class CommunityFragment : BaseVmFragment<FragmentCommunityBinding>(
    R.layout.fragment_community,
    CommunityViewModel::class.java
) {
    override val viewModel by lazy { vm as CommunityViewModel }

    lateinit var tab1: SharedInfoListFragment
    lateinit var tab2: ChatterListFragment
    lateinit var tab3: FeedListFragment

    private var isFabOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFabClickEvent()
    }

    override fun initFragment() {

        tab1 = SharedInfoListFragment()
        tab2 = ChatterListFragment()
        tab3 = FeedListFragment()

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

    private fun setFabClickEvent(){
        binding.fabPosting.setOnClickListener {
            toggleFab()
        }

        binding.fabChatter.setOnClickListener {
            //수다방에 포스팅하기, false
            startActivity(intentFor<PostWriteActivity>("postState" to false))


        }

        binding.fabShredInfo.setOnClickListener {
            //정보공유방에 포스팅하기, true
            startActivity(intentFor<PostWriteActivity>("postState" to true))

        }
    }

    private fun toggleFab(){
        if (isFabOpen){
            ObjectAnimator.ofFloat(binding.fabShredInfo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabChatter, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPosting, View.ROTATION, 45f, 0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.fabShredInfo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabChatter, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPosting, View.ROTATION, 0f, 45f).apply { start() }
        }
        isFabOpen = !isFabOpen
    }

    fun showTab1(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_community, tab1)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    fun showTab2(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_community, tab2)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    fun showTab3(){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout_community, tab3)
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }
}

