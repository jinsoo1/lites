package com.theshine.android.lites.ui.view.main.community.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class RegionSelectViewModel : BaseViewModel() {

    private val _action : MutableLiveData<Event<RegionAction>> = MutableLiveData()
    val action : LiveData<Event<RegionAction>> get() = _action

    private val _selectedButton : MutableLiveData<Int> = MutableLiveData()
    val selectedButton : LiveData<Int> get() = _selectedButton

    private val _selectedRegion : MutableLiveData<String> = MutableLiveData()
    val selectedRegion : LiveData<String> get() = _selectedRegion

    fun selectBtn(int: Int){
        _selectedButton.value = int
    }

    fun selectRegion(region : String){
        _selectedRegion.value = region
    }

    fun moveNext(){
        _action.value = Event(RegionAction.NEXT)
    }
    fun finishAction(){
        _action.value = Event(RegionAction.FINISH)
    }

    enum class RegionAction{
        NEXT, FINISH
    }


}