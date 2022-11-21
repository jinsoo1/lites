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

    private val _petData : MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val petData : LiveData<Event<Boolean>> get() = _petData

    private val _myPetWeightData : MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val myPetWeightData : LiveData<Event<Boolean>> get() = _myPetWeightData



    fun myPetSetting(item : PetData){
        _myPet.value = item
    }

    fun myPetWeightTrue(state : Boolean){
        _myPetWeightData.value = Event(state)
    }

    fun petDataTrue(state : Boolean){
        _petData.value = Event(state)
    }

    fun setFirstPosition(){
        _action.value = Event(MainAction.FIRST_POSITION)
    }

    enum class MainAction{
        FIRST_POSITION
    }

}