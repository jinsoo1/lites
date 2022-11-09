package com.theshine.android.lites.ui.view.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class InfoViewModel(
    private var petDataSource: PetDataSource
) : BaseViewModel() {

    val type: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val birth: MutableLiveData<String> = MutableLiveData()
    val variety : MutableLiveData<String> = MutableLiveData()
    val gender: MutableLiveData<Boolean> = MutableLiveData() //여아(false), 남아(true)
    val neutralization: MutableLiveData<Boolean> = MutableLiveData() //중성화 안함(false), 중성화 함(true)
    val moisture: MutableLiveData<String> = MutableLiveData()
    val protein: MutableLiveData<String> = MutableLiveData()
    val fat: MutableLiveData<String> = MutableLiveData()
    val fiber: MutableLiveData<String> = MutableLiveData()
    val ash: MutableLiveData<String> = MutableLiveData()
    val bcs : MutableLiveData<Int> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val petToken: MutableLiveData<String> = MutableLiveData()
    val finishAction: MutableLiveData<Event<String>> = MutableLiveData()

    fun insertPetData(){
        petDataSource.insertPet(
            type = type.value!!,
            name = name.value!!,
            birth = birth.value!!,
            variety = variety.value!!,
            gender = gender.value!!,
            neutralization = neutralization.value!!,
            bcs = bcs.value!!,
            moisture = moisture.value!!,
            protein = protein.value!!,
            fat = fat.value!!,
            fiber = fiber.value!!,
            ash = ash.value!!
        )
            .doOnSubscribe {
                isLoading.value = true
                Log.d("테스트중1", "111")
            }
            .doOnError {
                isLoading.value = false
                Log.d("테스트중1", "222")
            }
            .subscribe({
                isLoading.value = false
                finishAction.value = Event("finish")
                Log.d("테스트중1", "token : "+it.toString())

            },{
                Log.d("테스트중1", "에러")
            })
            .addTo(compositeDisposable)
    }

}