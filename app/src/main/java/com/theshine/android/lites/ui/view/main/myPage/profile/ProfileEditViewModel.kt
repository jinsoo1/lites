package com.theshine.android.lites.ui.view.main.myPage.profile

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.App
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.Profile
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyData
import com.theshine.android.lites.ui.view.info.variety.VarietyListAdapter
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.info.variety.dog_variety
import com.theshine.android.lites.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.jetbrains.anko.db.INTEGER
import java.io.File

class ProfileEditViewModel(
    private var petDataSource: PetDataSource
): BaseViewModel() {

    val action: MutableLiveData<Event<ProfileEditActions>> = MutableLiveData()
    val finishAction: MutableLiveData<Event<String>> = MutableLiveData()

    private val _bcsStep : MutableLiveData<List<BcsData>> = MutableLiveData(listOf())
    val bcsStep : MutableLiveData<List<BcsData>> get() = _bcsStep

    private val _bcsSelect : MutableLiveData<Event<BcsData>> = MutableLiveData()
    val bcsSelect : MutableLiveData<Event<BcsData>> get() = _bcsSelect

    private val _dogVariety : MutableLiveData<List<VarietyData>> = MutableLiveData(listOf())
    val dogVariety : MutableLiveData<List<VarietyData>> get() = _dogVariety

    private val _varietySelect : MutableLiveData<Event<VarietyData>> = MutableLiveData()
    val varietySelect : MutableLiveData<Event<VarietyData>> get() = _varietySelect

    val petToken: MutableLiveData<String> = MutableLiveData("")
    val profileImage: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val birth: MutableLiveData<String> = MutableLiveData()
    val birth2: MutableLiveData<String> = MutableLiveData() //만나이
    val variety : MutableLiveData<String> = MutableLiveData()
    val gender: MutableLiveData<Int> = MutableLiveData() //여아(false), 남아(true)/ 0 여아, 1 남아
    val neutralization: MutableLiveData<Int> = MutableLiveData() //중성화 안함(false), 중성화 함(true)/ 0 안함, 1함
    val bcs : MutableLiveData<Int> = MutableLiveData()
    val bcsString: MutableLiveData<String> = MutableLiveData()

    var isProfileImageEdited = false


    init {
        var bcsData : MutableList<BcsData> = mutableListOf()
        bcs_step.forEach{
            bcsData.add(BcsData(it))
        }
        _bcsStep.value = bcsData

        var varietyData : MutableList<VarietyData> = mutableListOf()
        dog_variety.forEach {

            varietyData.add(VarietyData(it))
        }
        _dogVariety.value = varietyData

        bcsString
    }



    fun selectedItem(item : BcsData){
        _bcsSelect.value = Event(item)
        _bcsStep.value?.forEach {
            it.isChecked.set(false)
        }

        item.isChecked.set(true)
    }

    fun setBirthDay(){
        action.value = Event(ProfileEditActions.BIRTH)
    }

    fun selectedVariety(item : VarietyData){
        _varietySelect.value = Event(item)
        _dogVariety.value?.forEach {
            it.isChecked.set(false)
        }

        item.isChecked.set(true)

    }

    fun clickFemale(){
        action.value = Event(ProfileEditActions.FEMALE)

    }

    fun clickMale(){
        action.value = Event(ProfileEditActions.MALE)

    }

    fun clickO(){
        action.value = Event(ProfileEditActions.O)
    }

    fun clickX(){
        action.value = Event(ProfileEditActions.X)
    }

    fun moveGallery(){
        action.value = Event(ProfileEditActions.GALLERY)
    }

    fun editComplete(){
       action.value = Event(ProfileEditActions.UPDATE)

    }

    fun updateProfile(){

        val file = if (isProfileImageEdited) {

            val file = File(profileImage.value ?: "")
            MultipartBody.Part.createFormData(
                "profileImg", file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        } else null



        petDataSource.updatePetProfile(
            petToken = (petToken.value ?: "").toRequestBody(),
            name = (name.value ?: "").toRequestBody(),
            birth = (birth.value ?: "").toRequestBody(),
            variety = (variety.value ?: "").toRequestBody(),
            profileImage = file,
            gender = (gender.value.toString()).toRequestBody(),
            neutralization = (neutralization.value.toString()).toRequestBody(),
            bcs = (bcs.value.toString()).toRequestBody()
        ).subscribe({
            Log.d("updateProfile", it.toString())
            Log.d("updateProfile", birth.value!!.toString())
            finishAction.value = Event("finish")
        },{
            Log.d("updateProfile_에러", it.toString())
        })
            .addTo(compositeDisposable)
        Log.d("updateProfile", "update!!")

    }

    fun setOnClickXbtn(){
        finishAction.value = Event("clickX")
    }

    enum class ProfileEditActions {
       UPDATE, FEMALE, MALE, O, X, BIRTH, GALLERY
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindBcsList")
        fun bindBcsList(rv : RecyclerView, list : List<BcsData>){
            val adapter = rv.adapter as BcsListAdapter
            adapter.let {
                it.updateItems(list)
            }
        }

        @JvmStatic
        @BindingAdapter("bindVarietyLists")
        fun bindVarietyLists(rv : RecyclerView, list : List<VarietyData>){
            val adapter = rv.adapter as VarietyListAdapterr?
            adapter.let {
                it?.updateItems(list)
            }
        }
    }
}