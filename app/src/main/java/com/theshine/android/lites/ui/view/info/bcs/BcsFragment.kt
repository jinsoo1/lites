package com.theshine.android.lites.ui.view.info.bcs

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.Bcs
import com.theshine.android.lites.databinding.FragmentInfoBcsBinding
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.databinding.ItemBcsListBinding
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

class BcsAdapter(vm: BcsViewModel) : BaseRecyclerAdapter<Bcs, ItemBcsListBinding>(
    R.layout.item_bcs_list, vm
){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        (holder.binding as ItemBcsListBinding).apply {


        }
    }
}