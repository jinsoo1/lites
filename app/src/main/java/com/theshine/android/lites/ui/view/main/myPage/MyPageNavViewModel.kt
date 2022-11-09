package com.theshine.android.lites.ui.view.main.myPage

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo

class MyPageNavViewModel(
    private var petDataSource: PetDataSource
): BaseViewModel() {

    val action: MutableLiveData<Event<MyPageActions>> = MutableLiveData()

    val type: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val birth: MutableLiveData<String> = MutableLiveData()
    val variety : MutableLiveData<String> = MutableLiveData()
    val gender: MutableLiveData<Boolean> = MutableLiveData() //여아(false), 남아(true)
    val genderInt: MutableLiveData<Int> = MutableLiveData()
    val neutralization: MutableLiveData<Boolean> = MutableLiveData() //중성화 안함(false), 중성화 함(true)
    val neutralizationInt: MutableLiveData<Int> = MutableLiveData()
    val moisture: MutableLiveData<String> = MutableLiveData()
    val protein: MutableLiveData<String> = MutableLiveData()
    val fat: MutableLiveData<String> = MutableLiveData()
    val fiber: MutableLiveData<String> = MutableLiveData()
    val ash: MutableLiveData<String> = MutableLiveData()
    val bcs : MutableLiveData<Int> = MutableLiveData()

    var isLoadedOnce: Boolean = false
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val profileList: MutableLiveData<List<PetData>> = MutableLiveData(listOf())

    val petToken: MutableLiveData<String> = MutableLiveData()

    init {
        initProfileList()
    }

    fun initProfileList(){
        val petToken = petToken.value ?: return
        petDataSource
            .getMyPetList(petToken)
            .map {
                it.map{
                    PetData(
                        it.petToken,
                        it.type,
                        it.name,
                        it.birth,
                        it.variety,
                        it.gender,
                        it.neutering,
                        it.bcs,
                        it.profileImg,
                        it.moisture,
                        it.protein,
                        it.fat,
                        it.fiber,
                        it.ash
                    )
                }
            }
            .doOnSubscribe { isLoading.value = true }
            .doOnError { isLoading.value = false }
            .onUI {
                profileList.value = it
                isLoading.value = false
                isLoadedOnce = true
            }
            .addTo(compositeDisposable)
    }


    fun notice(){
        action.value = Event(MyPageActions.NOTICE)
    }
    fun event(){
        action.value = Event(MyPageActions.EVENT)
    }
    fun inquiry(){
        action.value = Event(MyPageActions.INQUIRY)
    }
    fun setting(){
        action.value = Event(MyPageActions.SETTING)
    }
    fun management(){
        action.value = Event(MyPageActions.MANAGEMENT)
    }
    fun info(){
        action.value = Event(MyPageActions.INFO)
    }
    fun clickSharedInfo(){
        action.value = Event(MyPageActions.SHAREDINFO)
    }
    fun clickChatRoom(){
        action.value = Event(MyPageActions.CHATROOM)
    }

    fun adPet(){
        action.value = Event(MyPageActions.ADPET)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindProfileList")
        fun bindProfileList(rv: RecyclerView, list: List<PetData>){
            val adapter = rv.adapter as ProfileListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }

    enum class MyPageActions {
        NOTICE, EVENT, INQUIRY, SETTING, MANAGEMENT, INFO, SHAREDINFO, CHATROOM, ADPET
    }
}