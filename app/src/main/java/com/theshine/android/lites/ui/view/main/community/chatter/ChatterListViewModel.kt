package com.theshine.android.lites.ui.view.main.community.chatter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.SharedInfoData
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListAdapter
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class ChatterListViewModel(
    private val feedDataSource: FeedDataSource
) : BaseViewModel() {

    private val _action : MutableLiveData<Event<SharedInfoData>> = MutableLiveData()
    val action : LiveData<Event<SharedInfoData>> get() = _action

    private val _sharedInfoList : MutableLiveData<List<SharedInfoData>> = MutableLiveData(listOf())
    val sharedInfoList: LiveData<List<SharedInfoData>> get() = _sharedInfoList


    fun getChatterFeed(region : String){
        feedDataSource.chatterFeed(1000, 1, region)
            .map {
                it.map {
                    SharedInfoData(
                        it.feedToken,
                        it.title,
                        it.content,
                        it.createdAt,
                        it.name,
                        it.profileImg,
                        it.Photo,
                        it.likeCount,
                        it.commentCount,
                        it.visitCount
                    )
                }
            }
            .subscribe({
                _sharedInfoList.value = it
                Log.d("sharedChatter", it.toString())
            },{
                Log.d("sharedChatter E ", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun onItemClick(item : SharedInfoData){
        _action.value = Event(item)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindChatterList")
        fun bindChatterList(view: RecyclerView, list: List<SharedInfoData>) {
            val adapter = view.adapter as ChatterListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }
}