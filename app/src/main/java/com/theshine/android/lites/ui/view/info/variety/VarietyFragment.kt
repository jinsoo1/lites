package com.theshine.android.lites.ui.view.info.variety

import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoSelectBinding
import com.theshine.android.lites.databinding.FragmentInfoVarietyBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameFragmentDirections
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.select.SelectViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class VarietyFragment : BaseVmFragment<FragmentInfoVarietyBinding>(
R.layout.fragment_info_variety,
    VarietyViewModel::class.java
) {
    override val viewModel by lazy { vm as VarietyViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun VarietyViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                VarietyViewModel.VarietyActions.NEXT -> {

                    val action = VarietyFragmentDirections.actionVarietyFragmentToPhysicalFragment()
                    findNavController().navigate(action)

                }
                VarietyViewModel.VarietyActions.FEMALE -> {
                    activityViewModel.gender.value = false

                }
                VarietyViewModel.VarietyActions.MALE -> {
                    activityViewModel.gender.value = true

                }
                VarietyViewModel.VarietyActions.O -> {
                    activityViewModel.neutralization.value = true

                }
                VarietyViewModel.VarietyActions.X -> {
                    activityViewModel.neutralization.value = false

                }
            }
        })

    }
}