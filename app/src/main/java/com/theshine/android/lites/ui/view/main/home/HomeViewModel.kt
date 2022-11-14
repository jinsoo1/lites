package com.theshine.android.lites.ui.view.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData

class HomeViewModel: BaseViewModel() {

    private val _myPet : MutableLiveData<PetData> = MutableLiveData()
    val myPet : LiveData<PetData> get() = _myPet



    fun myPetSetting(item : PetData){
        _myPet.value = item
    }
}