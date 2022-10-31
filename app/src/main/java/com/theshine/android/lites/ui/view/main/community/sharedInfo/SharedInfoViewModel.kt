package com.theshine.android.lites.ui.view.main.community.sharedInfo

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.CommentList


class SharedInfoViewModel: BaseViewModel() {

    val commentList: MutableLiveData<List<CommentList>> = MutableLiveData(listOf())

    companion object {
        @JvmStatic
        @BindingAdapter("bindSharedInfo")
        fun bindSharedInfo(view: RecyclerView, list: List<CommentList>) {
            val adapter = view.adapter as CommentAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }

}