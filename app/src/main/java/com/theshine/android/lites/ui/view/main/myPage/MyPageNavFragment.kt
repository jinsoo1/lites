package com.theshine.android.lites.ui.view.main.myPage

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import com.theshine.android.lites.ui.view.main.community.ReadyDialog
import com.theshine.android.lites.ui.view.main.community.ReadyListener
import com.theshine.android.lites.ui.view.main.myPage.profile.ProfileEditActivity
import com.theshine.android.lites.ui.view.main.myPage.profile.ProfileEditViewModel
import com.theshine.android.lites.util.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyPageNavFragment : BaseVmFragment<FragmentMypageNavBinding>(
    R.layout.fragment_mypage_nav,
    MyPageNavViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageNavViewModel }

    val activityViewModel by sharedViewModel<MyPageViewModel>()

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun initFragment() {

        binding.rvPetList.adapter = ProfileListAdapter(viewModel)

        viewModel.setObserves()


        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == RESULT_OK){
                //프로필 수정 페이지에서 수정된 데이터 받아서 고대로 프로필리스트 페이지로 돌아가서 보여줌
                viewModel.initProfileList()

            }

        }

    }

    fun MyPageNavViewModel.setObserves(){

        action.observe(viewLifecycleOwner, EventObserver{
            when (it){
                MyPageNavViewModel.MyPageActions.NOTICE -> {
                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToNoticeFragment()
                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.EVENT -> {
                    val dialog = ReadyDialog(object : ReadyListener {
                        override fun moveClick() {

                        }
                    })
                    dialog.show(requireActivity().supportFragmentManager, "")
//                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToEventFragment()
//                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.INQUIRY -> {
                    val dialog = ReadyDialog(object : ReadyListener {
                        override fun moveClick() {

                        }
                    })
                    dialog.show(requireActivity().supportFragmentManager, "")
//                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToInquiryFragment()
//                    findNavController().navigate(action)

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
                    val dialog = ReadyDialog(object : ReadyListener {
                        override fun moveClick() {

                        }
                    })
                    dialog.show(requireActivity().supportFragmentManager, "")
//                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToActivityFragment()
//                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.CHATROOM -> {
                    //내활동페이지의 수다방으로 이동
                    activityViewModel.tabState.value = true
                    val dialog = ReadyDialog(object : ReadyListener {
                        override fun moveClick() {

                        }
                    })
                    dialog.show(requireActivity().supportFragmentManager, "")
//                    val action = MyPageNavFragmentDirections.actionMyPageNavFragmentToActivityFragment()
//                    findNavController().navigate(action)

                }
                MyPageNavViewModel.MyPageActions.ADDPET -> {
                    //정보입력 페이지로 이동 infoActivity
                    val intent = Intent(requireContext(), InfoActivity::class.java)
                    intent.putExtra("myPage", true)
                    resultLauncher.launch(intent)
                }
            }
        })

        profileEditActions.observe(viewLifecycleOwner, EventObserver{
            //프로필 수정 페이지로 이동
            val intent = Intent(requireContext(), ProfileEditActivity::class.java)
            intent.putExtra("petToken", it.petToken)
            intent.putExtra("name", it.name)
            intent.putExtra("gender", it.gender)
            intent.putExtra("birth", it.birth)
            intent.putExtra("variety", it.variety)
            intent.putExtra("profileImage", it.profileImage)
            intent.putExtra("weight", it.weight)
            intent.putExtra("bcs", it.bcs)
            intent.putExtra("neutralization", it.neutralization)

            resultLauncher.launch(intent)
        })
    }
}

class ProfileListAdapter(vm: MyPageNavViewModel) : BaseRecyclerAdapter<Profile, ItemProfileListBinding>(
    R.layout.item_profile_list, vm
)