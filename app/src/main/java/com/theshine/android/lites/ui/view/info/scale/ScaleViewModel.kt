package com.theshine.android.lites.ui.view.info.scale

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.physical.PhysicalViewModel
import com.theshine.android.lites.util.Event

class ScaleViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<scaleActions>> = MutableLiveData()

    fun next() {
        action.value = Event(scaleActions.NEXT)
    }

    enum class scaleActions {
        NEXT
    }
}