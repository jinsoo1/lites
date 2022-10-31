package com.theshine.android.lites.ui.view.info.physical

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.util.Event

class PhysicalViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<PhysicalActions>> = MutableLiveData()


    fun next() {
        action.value = Event(PhysicalActions.NEXT)
    }

    enum class PhysicalActions {
        NEXT
    }
}