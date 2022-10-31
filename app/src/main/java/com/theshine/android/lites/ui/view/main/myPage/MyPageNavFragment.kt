package com.theshine.android.lites.ui.view.main.myPage

import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageBinding
import com.theshine.android.lites.databinding.FragmentMypageNavBinding
import com.theshine.android.lites.ui.view.info.select.SelectFragmentDirections
import com.theshine.android.lites.util.EventObserver

class MyPageNavFragment : BaseVmFragment<FragmentMypageNavBinding>(
    R.layout.fragment_mypage_nav,
    MyPageNavViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageNavViewModel }

    override fun initFragment() {

        viewModel.setObserves()

    }

    fun MyPageNavViewModel.setObserves(){

        action.observe(viewLifecycleOwner, EventObserver{
            when (it){
                MyPageNavViewModel.MyPageActions.NOTICE -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToNoticeFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.EVENT -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToEventFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.INQUIRY -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToInquiryFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.SETTING -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToSettingFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.MANAGEMENT -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToManagementFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.INFO -> {

                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToInfoFragment()
                    findNavController().navigate(action)

                }

            }
        })
    }
}