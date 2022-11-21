package com.theshine.android.lites.ui.view.main.myPage.notice

import android.content.Intent
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.NoticeList
import com.theshine.android.lites.databinding.FragmentMypageNoticeBinding
import com.theshine.android.lites.databinding.FragmentMypageSettingBinding
import com.theshine.android.lites.databinding.ItemNoticeListBinding
import com.theshine.android.lites.ui.view.main.myPage.inquiry.InquiryFragmentDirections
import com.theshine.android.lites.ui.view.main.myPage.setting.SettingViewModel
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver

class NoticeFragment : BaseVmFragment<FragmentMypageNoticeBinding>(
    R.layout.fragment_mypage_notice,
    NoticeViewModel::class.java
) {
    override val viewModel by lazy { vm as NoticeViewModel }

    override fun initFragment() {

        binding.btnBack.setOnClickListener {
            val action = InquiryFragmentDirections.actionInquiryFragmentToMyPageFragment()
            findNavController().navigate(action)
        }


        binding.rvNotice.adapter = NoticeAdapter(viewModel)

        viewModel.setObserves()

    }

    fun NoticeViewModel.setObserves(){
        noticeDetail.observe(viewLifecycleOwner, EventObserver{
            val intent = Intent(requireContext(), NoticeDetailActivity::class.java)
            intent.putExtra("idx", it.idx)
            intent.putExtra("title", it.title)
            intent.putExtra("content", it.content)
            intent.putExtra("createdAt", it.createdAt)
            startActivity(intent)
            Log.d("noticeDetail", it.toString())
        })
    }

}
class NoticeAdapter(vm: NoticeViewModel) : BaseRecyclerAdapter<NoticeList, ItemNoticeListBinding>(
    R.layout.item_notice_list, vm
)