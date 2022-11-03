package com.theshine.android.lites.ui.view.info.namebirth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.ui.view.info.select.SelectViewModel
import com.theshine.android.lites.util.Event

class NameViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<NameActions>> = MutableLiveData()

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : MutableLiveData<String> get() = _name

    private val _birth : MutableLiveData<String> = MutableLiveData()
    val birth : MutableLiveData<String> = _birth

    private val _selected : MutableLiveData<Boolean> = MutableLiveData(false)
    val selected : LiveData<Boolean> get() = _selected


    fun next(){
        action.value = Event(NameActions.NEXT)
    }

    fun falseSelected(){
        _selected.value = false
    }

    fun trueSelected(){
        _selected.value = true
    }

    fun setBirthDay(){
        action.value = Event(NameActions.BIRTH)
    }

    enum class NameActions {
        NEXT, BIRTH
    }
}