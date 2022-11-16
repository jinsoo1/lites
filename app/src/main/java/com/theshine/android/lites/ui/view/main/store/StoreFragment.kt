package com.theshine.android.lites.ui.view.main.store

import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentStoreBinding

class StoreFragment : BaseVmFragment<FragmentStoreBinding>(
    R.layout.fragment_store,
    StoreViewModel::class.java
) {
    override val viewModel by lazy { vm as StoreViewModel }

    override fun initFragment() {





    }

    override fun onResume() {
        super.onResume()
        findNavController().navigate(R.id.action_global_home_fragment)
    }
}