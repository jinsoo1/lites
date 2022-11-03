package com.theshine.android.lites.ui.view.info.bcs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.util.Event

class BcsViewModel: BaseViewModel() {

    val action: MutableLiveData<Event<BcsActions>> = MutableLiveData()
    
    private val _bcs : MutableLiveData<Int> = MutableLiveData()
    val bcs : MutableLiveData<Int> get() = _bcs

    private val _selected : MutableLiveData<Boolean> = MutableLiveData(false)
    val selected : LiveData<Boolean> get() = _selected

    fun next(){
        action.value = Event(BcsActions.NEXT)
    }

    fun selectedNext(){
        _selected.value = true
    }

    fun unSelectedNext(){
        _selected.value = false
    }
    
    fun onClickItem(item: Int){
        _bcs.value = item
    }

    enum class BcsActions {
        NEXT
    }
}