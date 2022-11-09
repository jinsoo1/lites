package com.theshine.android.lites.ui.view.info.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class SelectViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<SelectActions>> = MutableLiveData()

    private val _selected : MutableLiveData<Boolean> = MutableLiveData(false)
    val selected : LiveData<Boolean> get() = _selected

    fun next(){
        action.value = Event(SelectActions.NEXT)
    }

    fun clickDog(){
        action.value = Event(SelectActions.DOG)
        _selected.value = true
    }

    fun clickCat(){
        action.value = Event(SelectActions.CAT)
        _selected.value = true
    }

    fun resume(){
        _selected.value = false
    }

    enum class SelectActions {
        NEXT, DOG, CAT
    }
}