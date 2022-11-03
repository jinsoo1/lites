package com.theshine.android.lites.ui.view.info.physical

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoPhysicalBinding
import com.theshine.android.lites.databinding.FragmentInfoScaleBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameFragmentDirections
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.scale.ScaleViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PhysicalFragment : BaseVmFragment<FragmentInfoPhysicalBinding>(
    R.layout.fragment_info_physical,
    PhysicalViewModel::class.java
) {
    override val viewModel by lazy { vm as PhysicalViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun PhysicalViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                PhysicalViewModel.PhysicalActions.NEXT -> {

                    val action = PhysicalFragmentDirections.actionPhysicalFragmentToBcsFragment()
                    findNavController().navigate(action)

                }
            }
        })

    }
}