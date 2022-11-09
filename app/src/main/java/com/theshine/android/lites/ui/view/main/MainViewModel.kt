package com.theshine.android.lites.ui.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetInfo
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.source.PetDataSource
import io.reactivex.rxkotlin.addTo

class MainViewModel : BaseViewModel() {

    val _myPet : MutableLiveData<PetInfo> = MutableLiveData()
    val myPet : LiveData<PetInfo> get() = _myPet



    fun myPetSetting(item : PetInfo){
        _myPet.value = item
    }

}