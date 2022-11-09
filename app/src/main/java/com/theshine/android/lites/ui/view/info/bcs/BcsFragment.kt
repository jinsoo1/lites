package com.theshine.android.lites.ui.view.info.bcs

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.databinding.FragmentInfoBcsBinding
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyFragmentDirections
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.util.EventObserver
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BcsFragment : BaseVmFragment<FragmentInfoBcsBinding>(
    R.layout.fragment_info_bcs,
    BcsViewModel::class.java
) {
    override val viewModel by lazy { vm as BcsViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()


    override fun initFragment() {

        binding.tvName.text = activityViewModel.name.value.toString()


        if(activityViewModel.type.value == "강아지"){
            binding.step1.setBackgroundResource(R.drawable.illust_bcs_dog_step1)
            binding.step2.setBackgroundResource(R.drawable.illust_bcs_dog_step2)
            binding.step3.setBackgroundResource(R.drawable.illust_bcs_dog_step3)
            binding.step4.setBackgroundResource(R.drawable.illust_bcs_dog_step4)
            binding.step5.setBackgroundResource(R.drawable.illust_bcs_dog_step5)
            binding.step6.setBackgroundResource(R.drawable.illust_bcs_dog_step6)
            binding.step7.setBackgroundResource(R.drawable.illust_bcs_dog_step7)
            binding.step8.setBackgroundResource(R.drawable.illust_bcs_dog_step8)
            binding.step9.setBackgroundResource(R.drawable.illust_bcs_dog_step9)

        } else {
            binding.step1.setBackgroundResource(R.drawable.illust_bcs_cat_step1)
            binding.step2.setBackgroundResource(R.drawable.illust_bcs_cat_step2)
            binding.step3.setBackgroundResource(R.drawable.illust_bcs_cat_step3)
            binding.step4.setBackgroundResource(R.drawable.illust_bcs_cat_step4)
            binding.step5.setBackgroundResource(R.drawable.illust_bcs_cat_step5)
            binding.step6.setBackgroundResource(R.drawable.illust_bcs_cat_step6)
            binding.step7.setBackgroundResource(R.drawable.illust_bcs_cat_step7)
            binding.step8.setBackgroundResource(R.drawable.illust_bcs_cat_step8)
            binding.step9.setBackgroundResource(R.drawable.illust_bcs_cat_step9)

        }
        viewModel.setObserves()
    }

    fun BcsViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                BcsViewModel.BcsActions.NEXT -> {
                    val action = BcsFragmentDirections.actionBcsFragmentToIngredientFragment()
                    findNavController().navigate(action)

                }
            }
        })

        bcs.observe(this@BcsFragment, Observer {
            activityViewModel.bcs.value = it
            Log.d("테스트2", it.toString())
            if(it != null){
                selectedNext()
                if(it == 1){
                    binding.bcs1.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 2){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 3){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 4){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 5){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 6){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 7){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 8){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.box_bcs_selected)
                    binding.bcs9.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)

                }
                if(it == 9){
                    binding.bcs1.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs2.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs3.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs4.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs5.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs6.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs7.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs8.setBackgroundResource(R.drawable.bg_yellow_fffd_radius_6dp)
                    binding.bcs9.setBackgroundResource(R.drawable.box_bcs_selected)

                }
            }else{
                unSelectedNext()
            }

        })

        selected.observe(this@BcsFragment, Observer {
            binding.layoutNext.isEnabled = it
        })
    }
}