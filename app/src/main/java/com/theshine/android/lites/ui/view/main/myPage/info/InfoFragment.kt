package com.theshine.android.lites.ui.view.main.myPage.info

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentMypageInfoBinding

class InfoFragment : BaseVmFragment<FragmentMypageInfoBinding>(
    R.layout.fragment_mypage_info,
    MyPageInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as MyPageInfoViewModel }

    override fun initFragment() {

        try {
            val pInfo: PackageInfo = requireActivity().packageManager.getPackageInfo(requireContext().packageName, 0)
            val version = pInfo.versionName
            viewModel.currentVersion.value = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }
}