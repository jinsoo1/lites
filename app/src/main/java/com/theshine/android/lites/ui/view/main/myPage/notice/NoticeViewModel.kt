package com.theshine.android.lites.ui.view.main.myPage.notice

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.EventList
import com.theshine.android.lites.data.common.model.NoticeList
import com.theshine.android.lites.data.remote.source.MyPageDataSource
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.ui.view.main.myPage.event.EventAdapter
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.ext.StringExt
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo

class NoticeViewModel(
    private val myPageDataSource: MyPageDataSource
): BaseViewModel() {

    private val _noticeList:  MutableLiveData<List<NoticeList>> = MutableLiveData(listOf())
    val noticeList: MutableLiveData<List<NoticeList>> get() = _noticeList

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadedOnce: Boolean = false

    val noticeDetail: MutableLiveData<Event<NoticeList>> = MutableLiveData()

    init {
        initNoticeData()
    }


    fun initNoticeData(){
        myPageDataSource.noticeList(1000, 10)
            .map {
                it.map {
                    NoticeList(
                        it.idx,
                        it.title,
                        it.content,
                        StringExt.showDateAsShort(it.createdAt)
                    )
                }
            }
            .doOnSubscribe { isLoading.value = true }
            .doOnError{isLoading.value = false}
            .onUI {
                isLoading.value = false
                isLoadedOnce = true
                _noticeList.value = it
            }
            .addTo(compositeDisposable)
    }

    fun noticeDetail(item: NoticeList){
        noticeDetail.value = Event(item)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindNoticeList")
        fun bindNoticeList(rv : RecyclerView, list : List<NoticeList>){
            val adapter = rv.adapter as NoticeAdapter
            adapter.let {
                it.updateItems(list)
            }
        }
    }


}