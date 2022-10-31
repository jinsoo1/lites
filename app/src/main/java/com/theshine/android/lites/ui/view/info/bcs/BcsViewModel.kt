package com.theshine.android.lites.ui.view.info.bcs

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.util.Event

class BcsViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<BcsActions>> = MutableLiveData()

    fun next(){
        action.value = Event(BcsActions.NEXT)
    }

    enum class BcsActions {
        NEXT
    }
}