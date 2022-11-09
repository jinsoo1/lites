package com.theshine.android.lites.ui.view.info.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class IngredientViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<IngredientActions>> = MutableLiveData()

    private val _moisture : MutableLiveData<String> = MutableLiveData()
    val moisture : MutableLiveData<String> get() = _moisture

    private val _protein : MutableLiveData<String> = MutableLiveData()
    val protein : MutableLiveData<String> get() = _protein

    private val _fat : MutableLiveData<String> = MutableLiveData()
    val fat : MutableLiveData<String> get() = _fat

    private val _fiber : MutableLiveData<String> = MutableLiveData()
    val fiber : MutableLiveData<String> get() = _fiber

    private val _ash : MutableLiveData<String> = MutableLiveData()
    val ash : MutableLiveData<String> get() = _ash

    private val _selected : MutableLiveData<Boolean> = MutableLiveData(false)
    val selected : LiveData<Boolean> get() = _selected

    fun next() {
        action.value = Event(IngredientActions.NEXT)
    }

    fun skip() {
        action.value = Event(IngredientActions.SKIP)
    }

    fun selectedNext(){
        _selected.value = true
    }

    fun unSelectedNext(){
        _selected.value = false
    }

    enum class IngredientActions {
        NEXT, SKIP
    }
}