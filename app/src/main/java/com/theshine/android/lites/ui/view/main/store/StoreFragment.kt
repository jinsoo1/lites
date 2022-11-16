package com.theshine.android.lites.ui.view.main.store

import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentStoreBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class StoreFragment : BaseVmFragment<FragmentStoreBinding>(
    R.layout.fragment_store,
    StoreViewModel::class.java
) {
    override val viewModel by lazy { vm as StoreViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()


    override fun initFragment() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://shopping.naver.com/home"))
        startActivity(intent)



    }

//    override fun onResume() {
//        super.onResume()
//
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//        activityViewModel.setFirstPosition()
//    }
}