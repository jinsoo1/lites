package com.theshine.android.lites.ui.view.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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
    val profileImg : MutableLiveData<String> = MutableLiveData("")

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val petToken: MutableLiveData<String> = MutableLiveData()
    val finishAction: MutableLiveData<Event<String>> = MutableLiveData()
    val myPage : MutableLiveData<Boolean> = MutableLiveData()

    fun insertPetData(){
        val file = if (profileImg.value != "") {
            val file = File(profileImg.value ?: "")
            MultipartBody.Part.createFormData(
                "profileImg", file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        } else null

        if(moisture.value == null){
            petDataSource.insertMiniPet(
                type = (type.value ?: "").toRequestBody(),
                name = (name.value ?: "").toRequestBody(),
                birth = (birth.value ?: "").toRequestBody(),
                variety = (variety.value ?: "").toRequestBody(),
                gender = (gender.value.toString()).toRequestBody(),
                neutralization = (neutralization.value.toString()).toRequestBody(),
                bcs = (bcs.value.toString()).toRequestBody(),
                profileImg = file
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
                    Log.d("테스트중1", it.toString())
                })
                .addTo(compositeDisposable)
        }else{
            petDataSource.insertPet(
                type = (type.value ?: "").toRequestBody(),
                name = (name.value ?: "").toRequestBody(),
                birth = (birth.value ?: "").toRequestBody(),
                variety = (variety.value ?: "").toRequestBody(),
                gender = (gender.value.toString()).toRequestBody(),
                neutralization = (neutralization.value.toString()).toRequestBody(),
                bcs = (bcs.value.toString()).toRequestBody(),
                moisture = (moisture.value ?: "").toRequestBody(),
                protein = (protein.value ?: "").toRequestBody(),
                fat = (fat.value ?: "").toRequestBody(),
                fiber = (fiber.value ?: "").toRequestBody(),
                ash = (ash.value ?: "").toRequestBody(),
                profileImg = file
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

}