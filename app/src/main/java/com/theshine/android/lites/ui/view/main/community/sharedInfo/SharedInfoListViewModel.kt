package com.theshine.android.lites.ui.view.main.community.sharedInfo

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.SharedInfoData

class SharedInfoListViewModel : BaseViewModel() {

    val sharedInfoList: MutableLiveData<List<SharedInfoData>> = MutableLiveData(listOf())

    companion object {
        @JvmStatic
        @BindingAdapter("bindSharedInfoList")
        fun bindSharedInfoList(view: RecyclerView, list: List<SharedInfoData>) {
            val adapter = view.adapter as SharedInfoListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }
}