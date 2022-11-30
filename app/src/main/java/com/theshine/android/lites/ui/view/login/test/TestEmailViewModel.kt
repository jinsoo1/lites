package com.theshine.android.lites.ui.view.login.test

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.local.UserLoginLocalDataSource
import com.theshine.android.lites.data.remote.model.response.UserResponse
import com.theshine.android.lites.data.remote.source.AuthDataSource
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.ui.view.login.LoginViewModel
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class TestEmailViewModel(
    private val authDataSource: AuthDataSource,
    private val userLoginLocalDataSource: UserLoginLocalDataSource,
    private val petDataSource: PetDataSource
) : BaseViewModel(){

    val email: MutableLiveData<String> = MutableLiveData("")
    val action: MutableLiveData<Event<TestLoginActions>> = MutableLiveData()


    fun onLoginClicked() {
        val emailStr = email.value?.trim() ?: ""
        authDataSource
            .loginByGoogle("789756412356489798724", emailStr ?: "")
            .subscribe({
                onLoginSuccess(it)
            },{
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    //기기에 유저정보 저장
    private fun onLoginSuccess(response: UserResponse) {
        userLoginLocalDataSource.saveLoginInfo(response.user)
        userLoginLocalDataSource.saveAccessToken(response.accessToken)
        userLoginLocalDataSource.saveRefreshToken(response.refreshToken)
        successLogin()
    }

    fun successLogin(){

        petDataSource.petExistence()
            .subscribe({

                action.value = Event(TestLoginActions.MAIN)
            },{
                action.value = Event(TestLoginActions.PET)
            })
            .addTo(compositeDisposable)



//        action.value = Event(LoginActions.LOGIN)
    }

    enum class TestLoginActions {
       MAIN, PET
    }

}