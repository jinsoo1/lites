package com.theshine.android.lites.ui.view.info.namebirth

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.databinding.FragmentInfoPhysicalBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.physical.PhysicalViewModel
import com.theshine.android.lites.ui.view.info.select.SelectFragmentDirections
import com.theshine.android.lites.ui.view.info.select.SelectViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class NameFragment : BaseVmFragment<FragmentInfoNameBinding>(
    R.layout.fragment_info_name,
    NameViewModel::class.java
) {
    override val viewModel by lazy { vm as NameViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun NameViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                NameViewModel.NameActions.NEXT -> {

                    activityViewModel.name.value = binding.etName.text.toString()
                    activityViewModel.birth.value = binding.etBirth.text.toString()
                    Log.d("정보입력테스트", "이름 : "+binding.etName.text.toString())
                    Log.d("정보입력테스트", "생년월일 : "+binding.etBirth.text.toString())

                    val action = NameFragmentDirections.actionNameFragmentToVarietyFragment()
                    findNavController().navigate(action)

                }
            }
        })

    }
}