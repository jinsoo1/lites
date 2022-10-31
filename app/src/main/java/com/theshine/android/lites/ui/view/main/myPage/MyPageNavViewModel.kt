package com.theshine.android.lites.ui.view.main.myPage

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class MyPageNavViewModel: BaseViewModel() {

    val action: MutableLiveData<Event<MyPageActions>> = MutableLiveData()

    fun notice(){
        action.value = Event(MyPageActions.NOTICE)
    }
    fun event(){
        action.value = Event(MyPageActions.EVENT)
    }
    fun inquiry(){
        action.value = Event(MyPageActions.INQUIRY)
    }
    fun setting(){
        action.value = Event(MyPageActions.SETTING)
    }
    fun management(){
        action.value = Event(MyPageActions.MANAGEMENT)
    }
    fun info(){
        action.value = Event(MyPageActions.INFO)
    }

    enum class MyPageActions {
        NOTICE, EVENT, INQUIRY, SETTING, MANAGEMENT, INFO
    }
}