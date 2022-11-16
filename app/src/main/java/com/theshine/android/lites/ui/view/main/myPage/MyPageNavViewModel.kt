package com.theshine.android.lites.ui.view.main.myPage

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.Profile
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MyPageNavViewModel(
    private var petDataSource: PetDataSource
): BaseViewModel() {

    val action: MutableLiveData<Event<MyPageActions>> = MutableLiveData()

    val type: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val birth: MutableLiveData<String> = MutableLiveData()
    val birth2: MutableLiveData<String> = MutableLiveData() //만나이
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

    val age: MutableLiveData<String> = MutableLiveData()

    var isLoadedOnce: Boolean = false
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val profileList: MutableLiveData<List<Profile>> = MutableLiveData(listOf())
    val profileLists: MutableLiveData<List<Profile>> = MutableLiveData(listOf())

    val petToken: MutableLiveData<String> = MutableLiveData()

    val profileEditActions: MutableLiveData<Event<Profile>> = MutableLiveData()

    init {
        initProfileList()
    }

    /**
     * 생년월일을 기준으로 현재 나이 계산
     * @param unix unixtimestamp
     */

    open fun getAmericanAge(birthDate: String?): Int {
        val now: LocalDate = LocalDate.now()
        val parsedBirthDate: LocalDate =
            LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var americanAge: Int = now.minusYears(parsedBirthDate.getYear().toLong()).getYear() // (1)

        // (2)
        // 생일이 지났는지 여부를 판단하기 위해 (1)을 입력받은 생년월일의 연도에 더한다.
        // 연도가 같아짐으로 생년월일만 판단할 수 있다!
        if (parsedBirthDate.plusYears(americanAge.toLong()).isAfter(now)) {
            americanAge = americanAge - 1
        }
        return americanAge
    }

    fun initProfileList(){
        petDataSource
            .getMyPetList()
            .map {
                it.map{
                    Profile(
                        it.petToken,
                        it.name,
                        it.gender,
                        it.birth, //생년월일 데이터 yy-MM-dd
                        getAmericanAge(it.birth).toString(), //birth2 만 나이
                        it.variety,
                        it.profileImg,
                        it.weight,
                        it.bcs,
                        it.neutralization
                    )
                }
            }
            .doOnSubscribe { isLoading.value = true
                Log.d("initProfileList", "롸")}
            .doOnError { isLoading.value = false
                Log.d("initProfileList5", "ㅇ[라?")}
            .subscribe({
                profileList.value = it
                it.map {
                    if (it.gender == 0){
                        gender.value = false
                    } else {
                        gender.value = true
                    }
                }
                isLoading.value = false
                isLoadedOnce = true

                Log.d("initProfileList", it.toString())

            },{
                Log.d("initProfileList", "throw : "+it.toString())
            })
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
        action.value = Event(MyPageActions.ADDPET)
    }

    fun profileEdit(item: Profile){
        profileEditActions.value = Event(item)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bindProfileList")
        fun bindProfileList(rv: RecyclerView, list: List<Profile>){
            val adapter = rv.adapter as ProfileListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }

    enum class MyPageActions {
        NOTICE, EVENT, INQUIRY, SETTING, MANAGEMENT, INFO, SHAREDINFO, CHATROOM, ADDPET
    }
}