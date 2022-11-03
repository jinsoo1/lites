package com.theshine.android.lites.ui.view.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.local.pref.PreferencesController
import com.theshine.android.lites.data.remote.source.AuthDataSource
import com.theshine.android.lites.data.remote.source.PetDataSource
import io.reactivex.rxkotlin.addTo

class SplashViewModel (
    private val authDataSource: AuthDataSource,
    private val petDataSource: PetDataSource
) : BaseViewModel() {
    private var _action : MutableLiveData<SplashAction> = MutableLiveData()
    val action : MutableLiveData<SplashAction> = _action

    init {

        if(PreferencesController.userInfoPref.accessToken.isNotBlank()){
            authDataSource.validateAccessToken()
                .subscribe({
                    Log.d("authData", it.toString())
                    if(it.success){
                        //회원가입이 되어있을때
                        petDataSource
                            .petExistence()
                            .subscribe({
                                if(it.success){
                                    //반려동물 정보가 있을때
                                    _action.value = SplashAction.MOVE_MAIN
                                }else{
                                    //반려동물 정보가 없을때
                                    Log.d("petData", it.toString())
                                    _action.value = SplashAction.MOVE_LOGIN
                                }
                            },{
                                //회원가입 안되어있을때
                                Log.d("petData", it.toString())
                                _action.value = SplashAction.MOVE_LOGIN
                            })
                            .addTo(compositeDisposable)

                    }else{
                        Log.d("authData", it.toString())
                        _action.value = SplashAction.MOVE_LOGIN
                    }
                },{
                    Log.d("authData", it.toString())
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