package com.theshine.android.lites.ui.view.info.select

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class SelectViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<SelectActions>> = MutableLiveData()


    fun next(){
        action.value = Event(SelectActions.NEXT)
    }

    fun clickDog(){
        action.value = Event(SelectActions.DOG)
    }

    fun clickCat(){
        action.value = Event(SelectActions.CAT)
    }

    enum class SelectActions {
        NEXT, DOG, CAT
    }
}