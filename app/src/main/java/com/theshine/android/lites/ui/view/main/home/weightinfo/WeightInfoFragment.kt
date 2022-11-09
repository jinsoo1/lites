package com.theshine.android.lites.ui.view.main.home.weightinfo

import android.util.Log
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeMainBinding
import com.theshine.android.lites.databinding.FragmentWeightInfoBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.home.main.HomeMainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class WeightInfoFragment : BaseVmFragment<FragmentWeightInfoBinding>(
    R.layout.fragment_weight_info,
    WeightInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as WeightInfoViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()
    override fun initFragment() {
        Log.d("Fragment2", "Fragment")
        viewModel.setObserves()

        viewModel.setMyPetToken(activityViewModel.myPet.value)
        binding.textView2.text = activityViewModel.myPet.value!!.name + "(이)의 체중정보가 없습니다.\n체중계를 연동해 체중정보를 저장해주세요."


    }

    private fun WeightInfoViewModel.setObserves(){

        petToken.observe(this@WeightInfoFragment, Observer {
            getMyPetWeight(it)
        })

        myPetWeight.observe(this@WeightInfoFragment, Observer {
            getBCSData()
        })


    }

    override fun onResume() {
        super.onResume()

    }

}