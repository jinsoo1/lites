package com.theshine.android.lites.ui.view.main.myPage.inquiry

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.MyPageDataSource
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.util.Event
import java.text.SimpleDateFormat

class InquiryPostViewModel(
    private var myPageDataSource: MyPageDataSource
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
        myPageDataSource.postInquiry(
            inquiryToken = inquiryToken.value!!,
            title = title.value!!,
            createdAt = createdAt.value!!,
            content = content.value!!
        )
    }

    fun curcreatedAt(){
        val currentTime : Long = System.currentTimeMillis() // ms로 반환
        println(currentTime)

        val dataFormat1 = SimpleDateFormat("yyyy-MM-dd") // 년 월 일
        createdAt.value = dataFormat1.toString()

    }

    enum class InquiryPostActions{
        POST
    }

}