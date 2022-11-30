package com.theshine.android.lites.ui.view.main.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentCommunityBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.community.chatter.ChatterListFragment
import com.theshine.android.lites.ui.view.main.community.feed.FeedListFragment
import com.theshine.android.lites.ui.view.main.community.post.PostWriteActivity
import com.theshine.android.lites.ui.view.main.community.region.RegionSelectActivity
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListFragment
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CommunityFragment : BaseVmFragment<FragmentCommunityBinding>(
    R.layout.fragment_community,
    CommunityViewModel::class.java
) {
    override val viewModel by lazy { vm as CommunityViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()
    lateinit var tab1: SharedInfoListFragment
    lateinit var tab2: ChatterListFragment
    lateinit var tab3: FeedListFragment

    private var isFabOpen = false

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private lateinit var postLauncher : ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFabClickEvent()

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == AppCompatActivity.RESULT_OK){

                Log.d("resultLauncher1", result.data?.getIntExtra("regionInt",0).toString())
                Log.d("resultLauncher2", result.data?.getStringExtra("region").toString())
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.settingRegion( result.data?.getStringExtra("region").toString())
                    activityViewModel.regionDataTrue(result.data?.getStringExtra("region").toString())
                }


            }
        }

        postLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == AppCompatActivity.RESULT_OK){
                //refresh
            }
        }

        viewModel.setObserves()



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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val dialog = ReadyDialog(object : ReadyListener{
//            override fun moveClick() {
//                activityViewModel.setFirstPosition()
//            }
//
//        })
//        dialog.show(requireActivity().supportFragmentManager, "")
        //activityViewModel.setFirstPosition()

    }

    override fun initFragment() {



        binding.fabShredInfo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
        binding.fabChatter.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
        binding.fabShredInfo.isClickable = false
        binding.fabChatter.isClickable = false







    }

    private fun CommunityViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                CommunityViewModel.CommunityAction.REGION ->{
                    Log.d("TestsTASD", "ASdasd")
                    val intent = Intent(requireContext(), RegionSelectActivity::class.java)
                    resultLauncher.launch(intent)
                }
            }
        })
    }

    private fun setFabClickEvent(){
        binding.fabPosting.setOnClickListener {
            toggleFab()
        }

        binding.fabChatter.setOnClickListener {
            //수다방에 포스팅하기, false
            val intent = Intent(requireContext(), PostWriteActivity::class.java)
            intent.putExtra("postState", false)
            postLauncher.launch(intent)


        }

        binding.fabShredInfo.setOnClickListener {
            //정보공유방에 포스팅하기, true
            val intent = Intent(requireContext(), PostWriteActivity::class.java)
            intent.putExtra("postState", true)
            postLauncher.launch(intent)

        }
    }

    private fun toggleFab(){
        if (isFabOpen){
            binding.fabShredInfo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
            binding.fabChatter.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
//            ObjectAnimator.ofFloat(binding.fabShredInfo, "translationY", 0f).apply { start() }
//            ObjectAnimator.ofFloat(binding.fabChatter, "translationY", 0f).apply { start() }
//            ObjectAnimator.ofFloat(binding.fabPosting, View.ROTATION, 45f, 0f).apply { start() }
            binding.fabShredInfo.isClickable = false
            binding.fabChatter.isClickable = false
            isFabOpen = false
        } else {
            binding.fabShredInfo.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
            binding.fabChatter.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
//            ObjectAnimator.ofFloat(binding.fabShredInfo, "translationY", -360f).apply { start() }
//            ObjectAnimator.ofFloat(binding.fabChatter, "translationY", -180f).apply { start() }
//            ObjectAnimator.ofFloat(binding.fabPosting, View.ROTATION, 0f, 45f).apply { start() }
            binding.fabShredInfo.isClickable = true
            binding.fabChatter.isClickable = true
            isFabOpen = true
        }

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

