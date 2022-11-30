package com.theshine.android.lites.ui.view.info.select

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentCommunityBinding
import com.theshine.android.lites.databinding.FragmentInfoSelectBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.community.CommunityViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectFragment : BaseVmFragment<FragmentInfoSelectBinding>(
    R.layout.fragment_info_select,
    SelectViewModel::class.java
) {
    override val viewModel by lazy { vm as SelectViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun SelectViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                SelectViewModel.SelectActions.NEXT -> {

                    val action = SelectFragmentDirections.actionSelectFragmentToNameFragment()
                    findNavController().navigate(action)

                }
                SelectViewModel.SelectActions.DOG -> {
                    activityViewModel.type.value = "강아지"
                    binding.ivDog.setBackgroundResource(R.drawable.bg_oval_green)
                    binding.ivCat.setBackgroundResource(R.drawable.bg_oval_d9d9)
                }

                SelectViewModel.SelectActions.CAT -> {
                    activityViewModel.type.value = "고양이"
                    binding.ivCat.setBackgroundResource(R.drawable.bg_oval_green)
                    binding.ivDog.setBackgroundResource(R.drawable.bg_oval_d9d9)
                }
            }
        })

        selected.observe(this@SelectFragment, Observer {
            binding.layoutNext.isEnabled = it
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.resume()
    }
}