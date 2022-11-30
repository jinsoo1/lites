package com.theshine.android.lites.ui.view.activity.chatter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.SimpleSharedData
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.ui.view.activity.shared.UserFeedSharedListAdapter
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class UserChatterViewModel(
    private val feedDataSource: FeedDataSource
) : BaseViewModel() {

    private val _userToken : MutableLiveData<String> = MutableLiveData()
    val userToken : LiveData<String> get() = _userToken

    private val _myFeedData : MutableLiveData<List<SimpleSharedData>> = MutableLiveData(listOf())
    val myFeedData : MutableLiveData<List<SimpleSharedData>> get() = _myFeedData

    private val _onClickItemData : MutableLiveData<Event<SimpleSharedData>> = MutableLiveData()
    val onClickItemData : LiveData<Event<SimpleSharedData>> get() = _onClickItemData

    private val _feedState : MutableLiveData<Boolean> = MutableLiveData()
    val feedState : LiveData<Boolean> get() = _feedState


    fun settingUserToken(token : String){
        Log.d("onCreate(TokenViewM)", token)
        _userToken.value = token
    }

    fun settingFeedState(state : Boolean){
        _feedState.value = state
    }

    fun getUserChatterFeed(){

        feedDataSource.getUserSharedFeed(userToken.value!!, "수다방")
            .map {
                it.map {
                    SimpleSharedData(
                        it.feedToken,
                        it.title,
                        it.content,
                        it.createdAt,
                        it.Photo,
                        it.likeCount,
                        it.commentCount,
                        it.visitCount
                    )
                }
            }
            .subscribe({
                myFeedData.value = it
                Log.d("getUserChatterFeed", it.toString())
            },{
                Log.d("getUserChatterFeed E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun getUserSharedComment(){
        feedDataSource.getUserSharedComment(userToken.value!!, "수다방")
            .map {
                it.map {
                    SimpleSharedData(
                        it.feedToken,
                        it.title,
                        it.content,
                        it.createdAt,
                        it.Photo,
                        it.likeCount,
                        it.commentCount,
                        it.visitCount
                    ).apply {
                        this.activityState.set(true)
                    }
                }
            }
            .subscribe({
                myFeedData.value = it
                Log.d("getUserSharedFeed", it.toString())
            },{
                Log.d("getUserSharedFeed E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun onClickItem(item : SimpleSharedData){
        _onClickItemData.value = Event(item)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindUserFeedChatterList")
        fun bindUserFeedChatterList(view: RecyclerView, list: List<SimpleSharedData>) {
            val adapter = view.adapter as UserFeedChatterListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }
}