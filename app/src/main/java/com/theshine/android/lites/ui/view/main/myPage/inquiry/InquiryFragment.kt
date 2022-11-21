package com.theshine.android.lites.ui.view.main.myPage.inquiry

import android.content.Intent
import android.media.metrics.Event
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.InquiryList
import com.theshine.android.lites.databinding.FragmentMypageInquiryBinding
import com.theshine.android.lites.databinding.FragmentMypageManagementBinding
import com.theshine.android.lites.databinding.ItemInquiryListBinding
import com.theshine.android.lites.ui.view.main.myPage.info.InfoFragmentDirections
import com.theshine.android.lites.ui.view.main.myPage.management.ManagementViewModel
import com.theshine.android.lites.util.EventObserver

class InquiryFragment : BaseVmFragment<FragmentMypageInquiryBinding>(
    R.layout.fragment_mypage_inquiry,
    InquiryViewModel::class.java
) {
    override val viewModel by lazy { vm as InquiryViewModel }

    override fun initFragment() {

        binding.btnBack.setOnClickListener {
            val action = InquiryFragmentDirections.actionInquiryFragmentToMyPageFragment()
            findNavController().navigate(action)
        }

    }

    fun InquiryViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                InquiryViewModel.InquiryActions.POST -> {
                    val intent = Intent(requireContext(), InquiryDetailActivity::class.java)
                    startActivity(intent)
                }
            }
        })
        inquiryDetailActions.observe(viewLifecycleOwner, EventObserver{
            //디테일 페이지로 이동
            val intent = Intent(requireContext(), InquiryDetailActivity::class.java)
            intent.putExtra("inquiryToken", it.inquiryToken)
            startActivity(intent)
        })
    }
}

class InquiryListAdapter(vm: InquiryViewModel) : BaseRecyclerAdapter<InquiryList, ItemInquiryListBinding>(
    R.layout.item_inquiry_list
)