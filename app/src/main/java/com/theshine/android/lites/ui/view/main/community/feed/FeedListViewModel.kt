package com.theshine.android.lites.ui.view.main.community.feed

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.ImageFeed
import com.theshine.android.lites.data.common.model.SharedInfoData
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListAdapter
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class FeedListViewModel(
    private val feedDataSource: FeedDataSource
) : BaseViewModel() {

    private val _action : MutableLiveData<Event<ImageFeed>> = MutableLiveData()
    val action : LiveData<Event<ImageFeed>> get() = _action

    private val _imageFeedList : MutableLiveData<List<ImageFeed>> = MutableLiveData(listOf())
    val imageFeed : LiveData<List<ImageFeed>> get() = _imageFeedList


    fun getImageFeedItem(region : String){
        feedDataSource.imageFeed(1000,1, region)
            .map {
                it.map {
                    ImageFeed(
                        it.feedToken,
                        it.category,
                        it.photo
                    )
                }
            }
            .subscribe({
                _imageFeedList.value = it
                Log.d("imageFeed", it.toString())
            },{
                Log.d("imageFeed E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun onItemClick(item : ImageFeed){
        _action.value = Event(item)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindImageFeedList")
        fun bindImageFeedList(view: RecyclerView, list: List<ImageFeed>) {
            val adapter = view.adapter as ImageFeedListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }
}