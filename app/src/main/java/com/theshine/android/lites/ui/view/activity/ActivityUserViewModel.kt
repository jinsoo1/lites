package com.theshine.android.lites.ui.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel

class ActivityUserViewModel : BaseViewModel(){


    private val _userToken : MutableLiveData<String> = MutableLiveData()
    val userToken : LiveData<String> get() = _userToken


    fun settingUserToken(token : String){
        _userToken.value = token
    }


}