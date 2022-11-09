package com.theshine.android.lites.ui.view.main.myPage

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.PetData
import com.theshine.android.lites.data.common.model.Profile
import com.theshine.android.lites.databinding.FragmentMypageBinding
import com.theshine.android.lites.databinding.FragmentMypageNavBinding
import com.theshine.android.lites.databinding.ItemProfileListBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.select.SelectFragmentDirections
import com.theshine.android.lites.ui.view.main.MainActivity
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyPageNavFragment : BaseVmFragment<FragmentMypageNavBinding>(
    R.layout.fragment_mypage_nav,
    MyPageNavViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageNavViewModel }

    val activityViewModel by sharedViewModel<MyPageViewModel>()

    override fun initFragment() {

        binding.rvPetList.adapter = ProfileListAdapter(viewModel)

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

                MyPageNavViewModel.MyPageActions.SHAREDINFO -> {
                    //내활동페이지의 정보공유탭으로 이동
                    activityViewModel.tabState.value = false
                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToActivityFragment()
                    findNavController().navigate(action)


                }
                MyPageNavViewModel.MyPageActions.CHATROOM -> {
                    //내활동페이지의 수다방으로 이동
                    activityViewModel.tabState.value = true
                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToActivityFragment()
                    findNavController().navigate(action)


                }
                MyPageNavViewModel.MyPageActions.ADPET -> {
                    //정보입력 페이지로 이동 infoActivity
                    val intent = Intent(requireContext(), InfoActivity::class.java)
                    startActivity(intent)
                }

            }
        })
    }
}

class ProfileListAdapter(vm: MyPageNavViewModel) : BaseRecyclerAdapter<PetData, ItemProfileListBinding>(
    R.layout.item_profile_list, vm
){

}