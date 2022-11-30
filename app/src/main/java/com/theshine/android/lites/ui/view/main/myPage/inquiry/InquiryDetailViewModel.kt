package com.theshine.android.lites.ui.view.main.myPage.inquiry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.InquiryList
import com.theshine.android.lites.data.remote.source.SettingDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class InquiryDetailViewModel(
    private val settingDataSource: SettingDataSource
): BaseViewModel() {

    private val _action : MutableLiveData<Event<InquiryDetailAction>> = MutableLiveData()
    val action : LiveData<Event<InquiryDetailAction>> get() = _action

    private val _inquiryToken : MutableLiveData<String> = MutableLiveData()
    val inquiryToken : LiveData<String> get() = _inquiryToken

    private val _inquiry : MutableLiveData<InquiryList> = MutableLiveData()
    val inquiry : LiveData<InquiryList> get() = _inquiry



    fun getInquiryDetail(){
        settingDataSource.getInquiryDetail(inquiryToken.value!!)
            .subscribe({
                _inquiry.value = InquiryList(
                    it.inquiryToken,
                    it.title,
                    it.content,
                    it.createdAt,
                    it.answer,
                    it.isAnswer == 1
                )
            },{
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }


    fun settingInquiryToken(token : String){
        _inquiryToken.value = token
    }


    fun onClickBackPressed(){
        _action.value = Event(InquiryDetailAction.FINISH)
    }


    enum class InquiryDetailAction{
        FINISH
    }
}