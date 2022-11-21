package com.theshine.android.lites.ui.view.main.myPage.info

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageInfoBinding
import com.theshine.android.lites.ui.view.login.terms.TermsActivity
import com.theshine.android.lites.ui.view.main.myPage.activity.ActivityFragmentDirections
import com.theshine.android.lites.util.EventObserver
import java.time.LocalDate

class InfoFragment : BaseVmFragment<FragmentMypageInfoBinding>(
    R.layout.fragment_mypage_info,
    MyPageInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageInfoViewModel }

    override fun initFragment() {

        binding.btnBack.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMyPageFragment()
        findNavController().navigate(action)
        }

        binding.tvCreatedAt.text = LocalDate.now().toString()

        try {
            val pInfo: PackageInfo = requireActivity().packageManager.getPackageInfo(requireContext().packageName, 0)
            val version = pInfo.versionName
            viewModel.currentVersion.value = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        viewModel.setObserves()

    }


    private fun MyPageInfoViewModel.setObserves(){
        action.observe(this@InfoFragment, EventObserver{
            when(it){
                MyPageInfoViewModel.MyPageInfoAction.ONE ->{
                    val intent = Intent(requireContext(), TermsActivity::class.java)
                    intent.putExtra("이용약관", "서비스이용약관")
                    startActivity(intent)
                }
                MyPageInfoViewModel.MyPageInfoAction.TWO ->{
                    val intent = Intent(requireContext(), TermsActivity::class.java)
                    intent.putExtra("이용약관", "개인정보약관")
                    startActivity(intent)
                }
            }
        })
    }
}