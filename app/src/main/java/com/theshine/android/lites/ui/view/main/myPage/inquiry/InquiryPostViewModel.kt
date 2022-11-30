package com.theshine.android.lites.ui.view.main.myPage.inquiry

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.MyPageDataSource
import com.theshine.android.lites.data.remote.source.SettingDataSource
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo
import java.text.SimpleDateFormat

class InquiryPostViewModel(
    private var settingDataSource: SettingDataSource
): BaseViewModel() {

    val action: MutableLiveData<Event<InquiryPostActions>> = MutableLiveData()

    val createdAt: MutableLiveData<String> = MutableLiveData()

    val inquiryToken: MutableLiveData<String> = MutableLiveData("")
    val title: MutableLiveData<String> = MutableLiveData("")
    val content: MutableLiveData<String> = MutableLiveData("")

    init {
        curcreatedAt()
    }

    fun post(){
        action.value = Event(InquiryPostActions.POST)

    }

    fun postInquiryData(){
        settingDataSource.postInquiry(
            title.value!!,
            content.value!!
            )
            .subscribe( {
                toast("문의사항 등록 완료")
                action.value = Event(InquiryPostActions.FINISH)
            },{
                Log.d("postInquiryData E", it.toString())
                })
            .addTo(compositeDisposable)
    }

    fun curcreatedAt(){
        val currentTime : Long = System.currentTimeMillis() // ms로 반환
        println(currentTime)

        val dataFormat1 = SimpleDateFormat("yyyy-MM-dd") // 년 월 일
        createdAt.value = dataFormat1.toString()

    }

    enum class InquiryPostActions{
        POST, FINISH
    }

}