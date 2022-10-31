package com.theshine.android.lites.ui.view.info.variety

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class VarietyViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<VarietyActions>> = MutableLiveData()


    fun next(){
        action.value = Event(VarietyActions.NEXT)
    }

    fun clickFemale(){
        action.value = Event(VarietyActions.FEMALE)

    }

    fun clickMale(){
        action.value = Event(VarietyActions.MALE)

    }

    fun clickO(){
        action.value = Event(VarietyActions.O)

    }

    fun clickX(){
        action.value = Event(VarietyActions.X)

    }

    enum class VarietyActions {
        NEXT, FEMALE, MALE, O, X
    }
}