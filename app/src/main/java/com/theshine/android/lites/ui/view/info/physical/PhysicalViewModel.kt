package com.theshine.android.lites.ui.view.info.physical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.util.Event

class PhysicalViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<PhysicalActions>> = MutableLiveData()

    private val _height : MutableLiveData<String> = MutableLiveData()
    val height : MutableLiveData<String> get() = _height

    private val _waist : MutableLiveData<String> = MutableLiveData()
    val waist : MutableLiveData<String> get() = _waist

    private val _selected : MutableLiveData<Boolean> = MutableLiveData(false)
    val selected : LiveData<Boolean> get() = _selected

    fun next() {
        action.value = Event(PhysicalActions.NEXT)
    }

    fun selectedNext(){
        _selected.value = true
    }

    fun unSelectedNext(){
        _selected.value = false
    }


    enum class PhysicalActions {
        NEXT
    }
}