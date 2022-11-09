package com.theshine.android.lites.ui.view.main.myPage.event

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.EventList
import com.theshine.android.lites.data.remote.source.MyPageDataSource
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo

class EventViewModel(
    private val myPageDataSource: MyPageDataSource
): BaseViewModel(
) {

    private val _eventList: MutableLiveData<List<EventList>> = MutableLiveData(listOf())
    val eventList: MutableLiveData<List<EventList>> get() = _eventList

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadedOnce: Boolean = false

    init {
        initEventData()
    }

    fun initEventData(){
        myPageDataSource.eventList(1000, 3)
            .map {
                it.map{
                    EventList(
                        it.eventToken,
                        it.eventTitle,
                        it.periodStart,
                        it.periodEnd,
                        it.image
                    )
                }
            }
            .doOnSubscribe { isLoading.value = true }
            .doOnError{isLoading.value = false }
            .onUI {
                isLoading.value = false
                isLoadedOnce = true
                _eventList.value = it
            }
            .addTo(compositeDisposable)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindEventList")
        fun bindEventList(rv : RecyclerView, list : List<EventList>){
            val adapter = rv.adapter as EventAdapter
            adapter.let {
                it.updateItems(list)
            }
        }
    }
}

