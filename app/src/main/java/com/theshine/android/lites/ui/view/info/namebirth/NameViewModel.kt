package com.theshine.android.lites.ui.view.info.namebirth

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.select.SelectViewModel
import com.theshine.android.lites.util.Event

class NameViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<NameActions>> = MutableLiveData()


    fun next(){
        action.value = Event(NameActions.NEXT)
    }

    enum class NameActions {
        NEXT
    }
}