package com.theshine.android.lites.ui.view.main.myPage.inquiry

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.InquiryList
import com.theshine.android.lites.data.common.model.Profile
import com.theshine.android.lites.data.remote.source.SettingDataSource
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.ui.view.main.myPage.ProfileListAdapter
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class InquiryViewModel(
    private val settingDataSource: SettingDataSource
): BaseViewModel() {

    val action: MutableLiveData<Event<InquiryActions>> = MutableLiveData()

    val inquiryToken: MutableLiveData<String> = MutableLiveData()
    val inquiryList: MutableLiveData<List<InquiryList>> = MutableLiveData(listOf())

    val inquiryDetailActions: MutableLiveData<Event<InquiryList>> = MutableLiveData()



    fun initInquiryList(){
        settingDataSource
            .getInquiryList()
            .map {
                it.map {
                    InquiryList(
                        it.inquiryToken,
                        it.title,
                        it.content,
                        it.createdAt,
                        it.answer ?: "답변이 아직 등록되지 않았습니다.",
                        it.isAnswer == 1
                    )
                }
            }
            .subscribe({
                inquiryList.value = it
            },{
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    fun postInquiry(){
        action.value = Event(InquiryActions.POST)
    }

    fun inquiryDetail(item: InquiryList){
        inquiryDetailActions.value = Event(item)

    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindInquiryList")
        fun bindInquiryList(rv: RecyclerView, list: List<InquiryList>){
            val adapter = rv.adapter as InquiryListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }


    enum class InquiryActions{
        POST
    }
}