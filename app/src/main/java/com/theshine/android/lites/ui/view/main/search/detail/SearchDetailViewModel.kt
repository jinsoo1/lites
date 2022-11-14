package com.theshine.android.lites.ui.view.main.search.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.SearchData
import com.theshine.android.lites.util.Event

class SearchDetailViewModel : BaseViewModel(){

    private val _action : MutableLiveData<Event<SearchDetailAction>> = MutableLiveData()
    val action : LiveData<Event<SearchDetailAction>> get() = _action

    private val _hospitalDetail : MutableLiveData<SearchData> = MutableLiveData()
    val hospitalDetail : LiveData<SearchData> get() = _hospitalDetail


    fun setHospitalData(item : SearchData){
        _hospitalDetail.value = item
    }

    fun moveHospitalUrl(){
        _action.value = Event(SearchDetailAction.URL)
    }

    fun callPhoneNum(){
        _action.value = Event(SearchDetailAction.CALL)
    }


    enum class SearchDetailAction{
        URL, CALL
    }
}