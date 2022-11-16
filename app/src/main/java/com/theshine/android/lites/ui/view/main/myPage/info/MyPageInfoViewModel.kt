package com.theshine.android.lites.ui.view.main.myPage.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class MyPageInfoViewModel: BaseViewModel() {

    private val _action : MutableLiveData<Event<MyPageInfoAction>> = MutableLiveData()
    val action : LiveData<Event<MyPageInfoAction>> get() = _action

    val currentVersion = MutableLiveData<String>()
    val latestVersion = MutableLiveData<String>()


    fun moveOne(){
        _action.value = Event(MyPageInfoAction.ONE)
    }

    fun moveTwo(){
        _action.value = Event(MyPageInfoAction.TWO)
    }

    enum class MyPageInfoAction(){
        ONE, TWO
    }
}