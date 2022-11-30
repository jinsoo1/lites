package com.theshine.android.lites.ui.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData
import com.theshine.android.lites.data.remote.source.UserDataSource
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo
import com.google.firebase.messaging.FirebaseMessaging


class MainViewModel(
    private val userDataSource: UserDataSource
) : BaseViewModel() {

    private val _action : MutableLiveData<Event<MainAction>> = MutableLiveData()
    val action : LiveData<Event<MainAction>> get() = _action

    private val _myPet : MutableLiveData<PetData> = MutableLiveData()
    val myPet : LiveData<PetData> get() = _myPet

    private val _petData : MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val petData : LiveData<Event<Boolean>> get() = _petData

    private val _myPetWeightData : MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val myPetWeightData : LiveData<Event<Boolean>> get() = _myPetWeightData

    private val _regionData : MutableLiveData<String> = MutableLiveData("전체지역")
    val regionData : LiveData<String> get() = _regionData


    init {
        initToken()
    }

    private fun initToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { result ->
            Log.e("asdf", "token update success\n${result}")
            userDataSource
                .updateFcmToken(result)
                .onUI {
                    Log.e("asdf", "token update success\n${result}")
                }
                .addTo(compositeDisposable)
        }

    }

    fun myPetSetting(item : PetData){
        _myPet.value = item
    }

    fun myPetWeightTrue(state : Boolean){
        _myPetWeightData.value = Event(state)
    }

    fun petDataTrue(state : Boolean){
        _petData.value = Event(state)
    }

    fun regionDataTrue(state: String?){

        Log.d("FeedListFragmentA", state.toString())
        _regionData.value = state
    }

    fun setFirstPosition(){
        _action.value = Event(MainAction.FIRST_POSITION)
    }

    enum class MainAction{
        FIRST_POSITION
    }

}