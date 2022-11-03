package com.theshine.android.lites.ui.view.login

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class LoginViewModel(
    private val petDataSource: PetDataSource
) : BaseViewModel() {

    val action: MutableLiveData<Event<LoginActions>> = MutableLiveData()

    fun kakaoLogin(){
        action.value = Event(LoginActions.KAKAO)
    }

    fun googleLogin(){
        action.value = Event(LoginActions.GOOGLE)
    }

    fun successLogin(){

        petDataSource.petExistence()
            .subscribe({
                action.value = Event(LoginActions.MAIN)
            },{
                action.value = Event(LoginActions.PET)
            })
            .addTo(compositeDisposable)



//        action.value = Event(LoginActions.LOGIN)
    }

    enum class LoginActions {
        KAKAO, GOOGLE, MAIN, PET
    }

}