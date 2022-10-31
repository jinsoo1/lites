package com.theshine.android.lites.ui.view.info.bcs

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoBcsBinding
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyFragmentDirections
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BcsFragment : BaseVmFragment<FragmentInfoBcsBinding>(
    R.layout.fragment_info_bcs,
    BcsViewModel::class.java
) {
    override val viewModel by lazy { vm as BcsViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        Log.d("정보입력테스트", activityViewModel.selected.toString()+activityViewModel.name.toString()+activityViewModel.birth.toString()+
                activityViewModel.height.toString()+activityViewModel.waist.toString())

        viewModel.setObserves()

    }

    fun BcsViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                BcsViewModel.BcsActions.NEXT -> {

                    val action = BcsFragmentDirections.actionBcsFragmentToScaleFragment()
                    findNavController().navigate(action)

                }
            }
        })
    }
}