package com.theshine.android.lites.ui.view.main.myPage.management

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.main.myPage.inquiry.InquiryPostViewModel
import com.theshine.android.lites.util.Event

class ManagementViewModel: BaseViewModel() {

    val action: MutableLiveData<Event<ManagementActions>> = MutableLiveData()

    fun logout(){
        action.value = Event(ManagementActions.LOGOUT)
    }

    enum class ManagementActions{
        LOGOUT
    }
}