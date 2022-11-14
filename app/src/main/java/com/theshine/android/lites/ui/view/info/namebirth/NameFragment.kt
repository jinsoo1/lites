package com.theshine.android.lites.ui.view.info.namebirth

import android.app.DatePickerDialog
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.util.EventObserver
import kotlinx.android.synthetic.main.activity_profile_edit.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

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

                NameViewModel.NameActions.BIRTH ->{
                    val myDatePicker =
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            val monthOfYear = month+1
                            val monthDay : String = if(monthOfYear < 10){
                                "0$monthOfYear"
                            }else{
                                monthOfYear.toString()
                            }
                            val days : String = if(dayOfMonth < 10){
                                "0$dayOfMonth"
                            }else{
                                dayOfMonth.toString()
                            }
                            binding.etBirth.text = "$year-$monthDay-$days"




                        }

                    val dialog = DatePickerDialog(
                        requireContext(),
                        myDatePicker,
                        2010,
                        7,
                        25
                    ).show()
                }
            }
        })

        selected.observe(this@NameFragment, Observer {
            binding.layoutNext.isEnabled = it
        })

        name.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

        birth.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

    }
}