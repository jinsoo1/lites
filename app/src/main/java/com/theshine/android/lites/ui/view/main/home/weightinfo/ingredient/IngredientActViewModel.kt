package com.theshine.android.lites.ui.view.main.home.weightinfo.ingredient

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class IngredientActViewModel(
    private val petDataSource: PetDataSource
) : BaseViewModel(){

    private val _action : MutableLiveData<Event<IngredientAction>> = MutableLiveData()
    val action : MutableLiveData<Event<IngredientAction>> get() = _action

    private val _petToken : MutableLiveData<String> = MutableLiveData()
    val petToken : MutableLiveData<String> get() = _petToken

    private val _moisture : MutableLiveData<String> = MutableLiveData()
    val moisture : MutableLiveData<String> get() = _moisture

    private val _protein : MutableLiveData<String> = MutableLiveData()
    val protein : MutableLiveData<String> get() = _protein

    private val _fat : MutableLiveData<String> = MutableLiveData()
    val fat : MutableLiveData<String> get() = _fat

    private val _fiber : MutableLiveData<String> = MutableLiveData()
    val fiber : MutableLiveData<String> get() = _fiber

    private val _ash : MutableLiveData<String> = MutableLiveData()
    val ash : MutableLiveData<String> get() = _ash

    fun saveIngredient(){
        if(moisture.value.isNullOrEmpty() || protein.value.isNullOrEmpty() || fat.value.isNullOrEmpty() || fiber.value.isNullOrEmpty()){
            toast("사료성분을 모두 적어주세요.(조회분 제외)")
        }else{
            petDataSource.updateIngredient(
                petToken.value ?: "",
                moisture.value ?: "",
                protein.value ?: "",
                fat.value ?: "",
                fiber.value ?: "",
                ash.value ?: ""
            )
                .subscribe({
                    Log.d("updateIngredient", it.toString())
                    action.value = Event(IngredientAction.FINISH)
                },{
                    Log.d("updateIngredient E", it.toString())
                })
                .addTo(compositeDisposable)
        }
    }

    fun settingPetToken(petToken : String){
        _petToken.value = petToken
    }

    enum class IngredientAction{
        FINISH
    }
}