package com.theshine.android.lites.ui.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData


class MainViewModel : BaseViewModel() {

    val _myPet : MutableLiveData<PetData> = MutableLiveData()
    val myPet : LiveData<PetData> get() = _myPet



    fun myPetSetting(item : PetData){
        _myPet.value = item
    }

}