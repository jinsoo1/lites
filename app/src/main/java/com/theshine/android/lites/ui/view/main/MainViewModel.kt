package com.theshine.android.lites.ui.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData
import com.theshine.android.lites.util.Event


class MainViewModel : BaseViewModel() {

    private val _action : MutableLiveData<Event<MainAction>> = MutableLiveData()
    val action : LiveData<Event<MainAction>> get() = _action

    private val _myPet : MutableLiveData<PetData> = MutableLiveData()
    val myPet : LiveData<PetData> get() = _myPet



    fun myPetSetting(item : PetData){
        _myPet.value = item
    }

    fun setFirstPosition(){
        _action.value = Event(MainAction.FIRST_POSITION)
    }

    enum class MainAction{
        FIRST_POSITION
    }

}