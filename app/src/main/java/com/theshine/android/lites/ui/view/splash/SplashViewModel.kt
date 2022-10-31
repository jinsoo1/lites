package com.theshine.android.lites.ui.view.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.local.pref.PreferencesController
import com.theshine.android.lites.data.remote.source.AuthDataSource
import io.reactivex.rxkotlin.addTo

class SplashViewModel (
    private val authDataSource: AuthDataSource
) : BaseViewModel() {
    private var _action : MutableLiveData<SplashAction> = MutableLiveData()
    val action : MutableLiveData<SplashAction> = _action

    init {

        if(PreferencesController.userInfoPref.accessToken.isNotBlank()){
            authDataSource.validateAccessToken()
                .subscribe({
                    Log.d("authData", it.message)
                    if(it.success){
                        _action.value = SplashAction.MOVE_MAIN

                    }else{
                        _action.value = SplashAction.MOVE_LOGIN
                    }
                },{
                    _action.value = SplashAction.MOVE_LOGIN
                    it.printStackTrace()
                })
                .addTo(compositeDisposable)

        }else{
            _action.value = SplashAction.MOVE_LOGIN
        }

    }

    enum class SplashAction{
        MOVE_LOGIN, MOVE_MAIN
    }
}