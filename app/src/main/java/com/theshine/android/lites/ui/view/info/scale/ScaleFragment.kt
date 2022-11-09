package com.theshine.android.lites.ui.view.info.scale

import android.content.Intent
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.databinding.FragmentInfoScaleBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.MainActivity
import com.theshine.android.lites.util.EventObserver
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ScaleFragment : BaseVmFragment<FragmentInfoScaleBinding>(
    R.layout.fragment_info_scale,
    ScaleViewModel::class.java
) {
    override val viewModel by lazy { vm as ScaleViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun ScaleViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                ScaleViewModel.scaleActions.NEXT -> {
                    activityViewModel.insertPetData()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                ScaleViewModel.scaleActions.STORE -> {
                    activityViewModel.insertPetData()

                }
                ScaleViewModel.scaleActions.COMMUNITY -> {
                    activityViewModel.insertPetData()

                }
            }
        })

    }
}