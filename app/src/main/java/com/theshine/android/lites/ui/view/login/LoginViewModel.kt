package com.theshine.android.lites.ui.view.login

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class LoginViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<LoginActions>> = MutableLiveData()

    fun kakaoLogin(){
        action.value = Event(LoginActions.KAKAO)
    }

    fun googleLogin(){
        action.value = Event(LoginActions.GOOGLE)
    }

    fun successLogin(){
        action.value = Event(LoginActions.LOGIN)
    }

    enum class LoginActions {
        KAKAO, GOOGLE, LOGIN
    }

}