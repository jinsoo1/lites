package com.theshine.android.lites.ui.view.info.variety

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoVarietyBinding
import com.theshine.android.lites.databinding.ItemVarietyBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameFragmentDirections
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class VarietyFragment : BaseVmFragment<FragmentInfoVarietyBinding>(
R.layout.fragment_info_variety,
    VarietyViewModel::class.java
) {
    override val viewModel by lazy { vm as VarietyViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        binding.btnBack.setOnClickListener{
            val action = VarietyFragmentDirections.actionVarietyFragmentToNameFragment()
            findNavController().navigate(action)
        }

        viewModel.setObserves()

        viewModel.type.value = activityViewModel.type.value

        binding.rvVariety.adapter = VarietyListAdapter(viewModel)

        BottomSheetBehavior.from(binding.sheetVariety).state = BottomSheetBehavior.STATE_HIDDEN

        binding.btnVariety.setOnClickListener {
            bottomSheetState()
        }

    }

    fun VarietyViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                VarietyViewModel.VarietyActions.NEXT -> {
                    activityViewModel.variety.value = binding.tvVariety.text.toString()
                    val action = VarietyFragmentDirections.actionVarietyFragmentToBcsFragment()
                    findNavController().navigate(action)

                }
                VarietyViewModel.VarietyActions.FEMALE -> {
                    activityViewModel.gender.value = false
                    selectedGender()
                    if(varietySelect.value != null && genderSelect.value == true && neutralizationSelect.value == true){
                        nextState.value = true
                    }
                    binding.tvFemale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
                    binding.tvMale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                }
                VarietyViewModel.VarietyActions.MALE -> {
                    activityViewModel.gender.value = true
                    selectedGender()
                    if(varietySelect.value != null && genderSelect.value == true && neutralizationSelect.value == true){
                        nextState.value = true
                    }
                    binding.tvFemale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                    binding.tvMale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
                }
                VarietyViewModel.VarietyActions.O -> {
                    activityViewModel.neutralization.value = true
                    selectedNeutralizationSelect()
                    if(varietySelect.value != null && genderSelect.value == true && neutralizationSelect.value == true){
                        nextState.value = true
                    }
                    binding.tvO.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
                    binding.tvX.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                }
                VarietyViewModel.VarietyActions.X -> {
                    activityViewModel.neutralization.value = false
                    selectedNeutralizationSelect()
                    if(varietySelect.value != null && genderSelect.value == true && neutralizationSelect.value == true){
                        nextState.value = true
                    }
                    binding.tvO.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                    binding.tvX.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
                }
            }
        })

        varietySelect.observe(viewLifecycleOwner, EventObserver{
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HIDDEN
            binding.tvVariety.text = it.variety
            if(varietySelect.value != null && genderSelect.value == true && neutralizationSelect.value == true){
                nextState.value = true
            }
        })

        nextState.observe(this@VarietyFragment, Observer {
            binding.layoutNext.isEnabled = it
        })

        type.observe(this@VarietyFragment, Observer {
            settingType()
        })

    }

    fun bottomSheetState(){
        if (BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_HALF_EXPANDED ||
            BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HIDDEN

        } else if (BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_HIDDEN ||
            BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HALF_EXPANDED
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.varietySelect.value = activityViewModel.variety.value?.let { Event(VarietyData(it)) }

        activityViewModel.gender.value?.let {
            if(it){
                viewModel.clickMale()
            }else{
                viewModel.clickFemale()
            }
        }

        activityViewModel.neutralization.value?.let {
            if(it){
                viewModel.clickO()
            }else{
                viewModel.clickX()
            }
        }
    }

}

class VarietyListAdapter(vm : VarietyViewModel) : BaseRecyclerAdapter<VarietyData, ItemVarietyBinding>(
    R.layout.item_variety, vm
)